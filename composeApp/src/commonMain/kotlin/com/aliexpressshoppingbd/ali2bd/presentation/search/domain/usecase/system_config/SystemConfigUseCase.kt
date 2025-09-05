package com.aliexpressshoppingbd.ali2bd.presentation.search.domain.usecase.system_config

import com.aliexpressshoppingbd.ali2bd.presentation.search.data.api_service.SearchApiService
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch

class SystemConfigUseCase(
    private val searchApiService: SearchApiService,
) {
    fun invoke(): Flow<Result<SystemConfigResponse>> = flow {
        try {
            val response = searchApiService.getSystemConfig()
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}