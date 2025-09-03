package com.aliexpressshoppingbd.ali2bd.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val user: UserInfo
)

@Serializable
data class UserInfo(
    val id: String,
    val username: String,
    val email: String,
    val name: String,
    val roles: List<String>
)

@Serializable
data class ErrorResponse(
    val status: Int,
    val message: String
)
