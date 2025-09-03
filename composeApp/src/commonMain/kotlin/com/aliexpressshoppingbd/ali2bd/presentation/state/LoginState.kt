package com.aliexpressshoppingbd.ali2bd.presentation.state

import com.aliexpressshoppingbd.ali2bd.model.LoginResponse

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val loginResponse: LoginResponse? = null,
    val errorMessage: String? = null,
    val username: String = "",
    val password: String = ""
)
