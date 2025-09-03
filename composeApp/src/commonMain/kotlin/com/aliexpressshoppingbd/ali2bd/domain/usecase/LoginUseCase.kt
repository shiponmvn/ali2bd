package com.aliexpressshoppingbd.ali2bd.domain.usecase

import com.aliexpressshoppingbd.ali2bd.model.LoginResponse
import com.aliexpressshoppingbd.ali2bd.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Flow<Result<LoginResponse>> {
        return authRepository.login(username, password)
    }
}
