package com.aliexpressshoppingbd.ali2bd.data.network

import com.aliexpressshoppingbd.ali2bd.model.LoginRequest
import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthApi(private val httpClient: HttpClient) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return httpClient.post {
            url("consumer/v1/auth/login")
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }.body()
    }
}
