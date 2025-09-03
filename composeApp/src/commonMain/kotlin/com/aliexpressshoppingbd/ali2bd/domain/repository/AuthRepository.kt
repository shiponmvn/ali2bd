package com.aliexpressshoppingbd.ali2bd.domain.repository

import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Flow<Result<LoginResponse>>
}
