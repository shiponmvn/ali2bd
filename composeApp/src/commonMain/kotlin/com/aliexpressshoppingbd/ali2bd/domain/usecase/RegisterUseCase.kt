package com.aliexpressshoppingbd.ali2bd.domain.usecase

import com.aliexpressshoppingbd.ali2bd.domain.repository.UserRepository
import com.aliexpressshoppingbd.ali2bd.model.RegisterRequest
import com.aliexpressshoppingbd.ali2bd.model.RegisterResponse

class RegisterUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
        name: String
    ): Result<RegisterResponse> {
        // Business logic validation
        if (username.isBlank()) {
            return Result.failure(Exception("Username cannot be empty"))
        }

        if (email.isBlank()) {
            return Result.failure(Exception("Email cannot be empty"))
        }

        if (!email.contains("@")) {
            return Result.failure(Exception("Please enter a valid email address"))
        }

        if (password.isBlank()) {
            return Result.failure(Exception("Password cannot be empty"))
        }

        if (password.length < 6) {
            return Result.failure(Exception("Password must be at least 6 characters"))
        }

        if (password != confirmPassword) {
            return Result.failure(Exception("Passwords do not match"))
        }

        if (name.isBlank()) {
            return Result.failure(Exception("Name cannot be empty"))
        }

        if (username.length < 3) {
            return Result.failure(Exception("Username must be at least 3 characters"))
        }

        val request = RegisterRequest(
            username = username,
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            name = name
        )
        return userRepository.register(request)
    }
}
