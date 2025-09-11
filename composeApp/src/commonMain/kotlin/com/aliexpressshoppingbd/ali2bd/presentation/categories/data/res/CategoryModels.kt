package com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res

data class CategoryItem(
    val name: String,
    val id: String
)

data class CategoriesResponse(
    val categories: List<CategoryItem> = emptyList()
)

data class ProductItem(
    val id: String,
    val title: String,
    val imageUrl: String,
    val price: String
)

data class CategoryProductsResponse(
    val products: List<ProductItem> = emptyList()
)
