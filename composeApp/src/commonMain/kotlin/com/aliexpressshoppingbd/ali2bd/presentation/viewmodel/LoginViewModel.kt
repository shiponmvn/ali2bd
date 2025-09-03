package com.aliexpressshoppingbd.ali2bd.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliexpressshoppingbd.ali2bd.domain.usecase.LoginUseCase
import com.aliexpressshoppingbd.ali2bd.presentation.state.LoginState
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun onUsernameChanged(username: String) {
        _state.value = _state.value.copy(
            username = username,
            errorMessage = null
        )
    }

    fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(
            password = password,
            errorMessage = null
        )
    }

    fun login() {
        val currentState = _state.value

        viewModelScope.launch {
            _state.value = currentState.copy(isLoading = true, errorMessage = null)

            val result = loginUseCase(currentState.username, currentState.password)

            result.fold(
                onSuccess = { loginResponse ->
                    _state.value = currentState.copy(
                        isLoading = false,
                        isSuccess = true,
                        loginResponse = loginResponse
                    )
                },
                onFailure = { exception ->
                    _state.value = currentState.copy(
                        isLoading = false,
                        isSuccess = false,
                        errorMessage = exception.message ?: "Unknown error occurred"
                    )
                }
            )
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(errorMessage = null)
    }
}
