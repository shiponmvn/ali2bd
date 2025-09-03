package com.aliexpressshoppingbd.ali2bd.presentation.login

import com.aliexpressshoppingbd.ali2bd.model.UserInfo

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val user: UserInfo? = null,
    val error: String? = null
)
