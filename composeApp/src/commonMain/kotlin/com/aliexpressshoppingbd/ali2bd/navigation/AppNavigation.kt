package com.aliexpressshoppingbd.ali2bd.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppNavigation {
    @Serializable
    data object Splash : AppNavigation()

    @Serializable
    data object Main : AppNavigation()

    @Serializable
    data object Search : AppNavigation()

    @Serializable
    data object ProductList : AppNavigation()
}
