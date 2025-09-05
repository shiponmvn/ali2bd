package com.aliexpressshoppingbd.ali2bd.presentation.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aliexpressshoppingbd.ali2bd.navigation.AppNavigation
import com.aliexpressshoppingbd.ali2bd.presentation.login.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import org.koin.compose.koinInject

@Composable
internal fun SplashNav(viewModel: LoginViewModel = koinInject(), navigateToMain: () -> Unit) {
    val navigator = rememberNavController()

    LaunchedEffect(Unit) {
        delay(2000) // Show splash for 2 seconds
        navigateToMain()
    }


    NavHost(
        startDestination = AppNavigation.Splash,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<AppNavigation.Splash> {
            SplashScreen ()
        }

    }

}