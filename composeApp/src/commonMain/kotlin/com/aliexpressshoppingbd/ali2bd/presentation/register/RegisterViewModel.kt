package com.aliexpressshoppingbd.ali2bd.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliexpressshoppingbd.ali2bd.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username, error = null) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, error = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, error = null) }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.update { it.copy(confirmPassword = confirmPassword, error = null) }
    }

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name, error = null) }
    }

    fun register() {
        val currentState = _uiState.value

        if (currentState.username.isBlank() || currentState.email.isBlank() ||
            currentState.password.isBlank() || currentState.confirmPassword.isBlank() ||
            currentState.name.isBlank()) {
            _uiState.update { it.copy(error = "All fields are required") }
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = registerUseCase(
                username = currentState.username,
                email = currentState.email,
                password = currentState.password,
                confirmPassword = currentState.confirmPassword,
                name = currentState.name
            )

            result.fold(
                onSuccess = { registerResponse ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRegistered = true,
                            user = registerResponse.user,
                            error = null
                        )
                    }
                },
                onFailure = { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "Registration failed"
                        )
                    }
                }
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
