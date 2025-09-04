package com.aliexpressshoppingbd.ali2bd.presentation.search.data.repository

import com.aliexpressshoppingbd.ali2bd.data.network.LoginApiService
import com.aliexpressshoppingbd.ali2bd.domain.repository.UserRepository
import com.aliexpressshoppingbd.ali2bd.model.LoginRequest
import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import com.aliexpressshoppingbd.ali2bd.model.RegisterRequest
import com.aliexpressshoppingbd.ali2bd.model.RegisterResponse
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.api_service.SearchApiService
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigResponse
import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.repository.SearchRepository
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val searchApiService:  SearchApiService
) : SearchRepository {

    override suspend fun systemConfig(): Flow<Result<SystemConfigResponse>>  = flow{
        try {
            val response = searchApiService.getSystemConfig();
            emit(Result.success(response))
        } catch (e: ClientRequestException) {
            emit(Result.failure(Exception("${e.message}")))
        } catch (e: Exception) {
            emit(Result.failure(Exception("${e.message}")))
        }
    }

}
