package com.aliexpressshoppingbd.ali2bd.data.network

import com.aliexpressshoppingbd.ali2bd.model.LoginRequest
import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import com.aliexpressshoppingbd.ali2bd.model.RegisterRequest
import com.aliexpressshoppingbd.ali2bd.model.RegisterResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class LoginApiService(
    private val httpClient: HttpClient
) {
    suspend fun login(request: LoginRequest): LoginResponse {
        return httpClient.post("https://edge.ali2bd.com/api/consumer/v1/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return httpClient.post("https://edge.ali2bd.com/api/consumer/v1/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}
