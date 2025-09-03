package com.aliexpressshoppingbd.ali2bd.data.repository

import com.aliexpressshoppingbd.ali2bd.model.LoginRequest
import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import com.aliexpressshoppingbd.ali2bd.data.network.AuthApi
import com.aliexpressshoppingbd.ali2bd.domain.repository.AuthRepository
import io.ktor.client.plugins.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun login(username: String, password: String): Flow<Result<LoginResponse>> = flow {
        try {
            val loginRequest = LoginRequest(username, password)
            val response = authApi.login(loginRequest)
            emit(Result.success(response))
        } catch (e: ClientRequestException) {
            emit(Result.failure(Exception("Authentication failed: ${e.message}")))
        } catch (e: Exception) {
            emit(Result.failure(Exception("An error occurred: ${e.message}")))
        }
    }
}
