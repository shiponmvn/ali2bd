package com.aliexpressshoppingbd.ali2bd.domain.usecase

import com.aliexpressshoppingbd.ali2bd.domain.repository.UserRepository
import com.aliexpressshoppingbd.ali2bd.model.LoginRequest
import com.aliexpressshoppingbd.ali2bd.model.LoginResponse

class LoginUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<LoginResponse> {
        // Business logic validation
        if (username.isBlank()) {
            return Result.failure(Exception("Username cannot be empty"))
        }

        if (password.isBlank()) {
            return Result.failure(Exception("Password cannot be empty"))
        }

        if (password.length < 6) {
            return Result.failure(Exception("Password must be at least 6 characters"))
        }

        val request = LoginRequest(username = username, password = password)
        return userRepository.login(request)
    }
}
