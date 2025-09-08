package com.aliexpressshoppingbd.ali2bd.presentation.productlist.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface ProductListNavigation {

    @Serializable
    data object ProductList : ProductListNavigation

}

