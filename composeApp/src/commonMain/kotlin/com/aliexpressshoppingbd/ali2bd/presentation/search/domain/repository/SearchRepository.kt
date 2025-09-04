package com.aliexpressshoppingbd.ali2bd.presentation.search.domain.repository

import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigItem
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun systemConfig(): Flow<Result<SystemConfigResponse>>
}
