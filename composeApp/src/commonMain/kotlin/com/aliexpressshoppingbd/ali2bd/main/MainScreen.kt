package com.aliexpressshoppingbd.ali2bd.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aliexpressshoppingbd.ali2bd.main.navigation.MainNavigationHost

@Composable
fun MainScreen(context: Any? = null, navigateToSplash: () -> Unit) {
    MainNavigationHost(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    )
}
