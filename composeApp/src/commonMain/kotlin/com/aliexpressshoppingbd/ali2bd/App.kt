package com.aliexpressshoppingbd.ali2bd

import androidx.compose.runtime.Composable
import com.aliexpressshoppingbd.ali2bd.di.appModule
import com.aliexpressshoppingbd.ali2bd.presentation.login.LoginScreen
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule())
    }) {
        LoginScreen(
            onLoginSuccess = {
                // Navigate to main screen after successful login
                // For now, we're just printing a success message
                println("Login successful!")
            }
        )
    }
}
