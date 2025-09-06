package com.aliexpressshoppingbd.ali2bd.presentation.search.data.api_service

import com.aliexpressshoppingbd.ali2bd.model.LoginRequest
import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import com.aliexpressshoppingbd.ali2bd.model.RegisterRequest
import com.aliexpressshoppingbd.ali2bd.model.RegisterResponse
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.statement.HttpResponse

interface SearchApiService {
    companion object {
        private const val TAG = "SearchApiService"
    }

    suspend fun getSystemConfig(): SystemConfigResponse
}
