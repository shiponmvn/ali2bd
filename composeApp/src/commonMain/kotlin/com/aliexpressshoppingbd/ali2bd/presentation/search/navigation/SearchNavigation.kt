package com.aliexpressshoppingbd.ali2bd.presentation.search.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface SearchNavigation {

    @Serializable
    data object Search : SearchNavigation
    @Serializable
    data class ProductList(val keyword: String) : SearchNavigation

}

