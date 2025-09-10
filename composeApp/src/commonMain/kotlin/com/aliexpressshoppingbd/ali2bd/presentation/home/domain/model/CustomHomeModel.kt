package com.aliexpressshoppingbd.ali2bd.presentation.home.domain.model

import com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.res.ProductItem
import kotlin.collections.emptyList

data class HomeProductSectionModel(
    val keyword: String? = null,
    val products: List<ProductItem> = emptyList()

)
