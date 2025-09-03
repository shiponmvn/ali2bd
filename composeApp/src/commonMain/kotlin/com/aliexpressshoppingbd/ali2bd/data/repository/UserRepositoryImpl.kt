package com.aliexpressshoppingbd.ali2bd.data.repository

import com.aliexpressshoppingbd.ali2bd.data.network.LoginApiService
import com.aliexpressshoppingbd.ali2bd.domain.repository.UserRepository
import com.aliexpressshoppingbd.ali2bd.model.LoginRequest
import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import com.aliexpressshoppingbd.ali2bd.model.RegisterRequest
import com.aliexpressshoppingbd.ali2bd.model.RegisterResponse

class UserRepositoryImpl(
    private val loginApiService: LoginApiService
) : UserRepository {

    override suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return try {
            val response = loginApiService.login(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(request: RegisterRequest): Result<RegisterResponse> {
        return try {
            val response = loginApiService.register(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
