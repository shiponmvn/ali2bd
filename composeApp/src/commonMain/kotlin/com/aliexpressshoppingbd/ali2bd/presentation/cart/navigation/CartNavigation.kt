package com.aliexpressshoppingbd.ali2bd.presentation.cart.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface CartNavigation {

    @Serializable
    data object Cart : CartNavigation

}
