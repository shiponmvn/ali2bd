package com.aliexpressshoppingbd.ali2bd.domain.repository

import com.aliexpressshoppingbd.ali2bd.model.LoginRequest
import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import com.aliexpressshoppingbd.ali2bd.model.RegisterRequest
import com.aliexpressshoppingbd.ali2bd.model.RegisterResponse

interface UserRepository {
    suspend fun login(request: LoginRequest): Result<LoginResponse>
    suspend fun register(request: RegisterRequest): Result<RegisterResponse>
}
