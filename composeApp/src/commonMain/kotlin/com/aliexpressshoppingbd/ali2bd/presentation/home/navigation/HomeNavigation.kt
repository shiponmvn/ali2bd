package com.aliexpressshoppingbd.ali2bd.presentation.home.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeNavigation {

    @Serializable
    data object Search : HomeNavigation

}

