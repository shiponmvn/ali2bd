package com.aliexpressshoppingbd.ali2bd.main.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class MainNavigationDestinations {
    @Serializable
    data object Home : MainNavigationDestinations()

    @Serializable
    data object Category : MainNavigationDestinations()

    @Serializable
    data object Cart : MainNavigationDestinations()

    @Serializable
    data object Account : MainNavigationDestinations()

    @Serializable
    data object Search : MainNavigationDestinations()
}
