package com.aliexpressshoppingbd.ali2bd.main.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class MainNavigationItem(
    val destination: MainNavigationDestinations,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String,
    val hasNotification: Boolean = false,
    val notificationCount: Int = 0
)
