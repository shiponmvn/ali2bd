package com.aliexpressshoppingbd.ali2bd.presentation.register

import com.aliexpressshoppingbd.ali2bd.model.UserInfo

data class RegisterUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val name: String = "",
    val isLoading: Boolean = false,
    val isRegistered: Boolean = false,
    val user: UserInfo? = null,
    val error: String? = null
)
