package com.aliexpressshoppingbd.ali2bd.presentation.search.domain.usecase.system_config

import com.aliexpressshoppingbd.ali2bd.model.LoginRequest
import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigResponse
import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SystemConfigUseCase(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(): Flow<Result<SystemConfigResponse>> {


        return searchRepository.systemConfig();

    }
}