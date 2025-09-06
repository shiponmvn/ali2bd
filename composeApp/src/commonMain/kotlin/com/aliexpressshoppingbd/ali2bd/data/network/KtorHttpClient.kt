package com.aliexpressshoppingbd.ali2bd.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json


object KtorHttpClient {


    @OptIn(ExperimentalSerializationApi::class)
    fun httpClient() = HttpClient {
        expectSuccess = false

        install(HttpTimeout) {
            val timeout = 60000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }

        // Add this log to verify when the HttpClient is created
        println("KtorHttpClient: Creating new HTTP client instance")
        install(ResponseObserver) {
            onResponse { response ->
                println("AppDebug HTTP ResponseObserver statu: ${response.status.value}")
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("Ktor Client: $message")
                }
            }
            level = LogLevel.ALL
        }

        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                val statusCode = response.status.value

                if (statusCode == 401) {
                    // Handle authentication if needed
                }

                // Log all responses for debugging
                println("Response validation: HTTP $statusCode")
            }
        }

        // Use the custom Json configuration from JsonConfig
        install(ContentNegotiation) {
            json(JsonConfig.customJson)
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

}