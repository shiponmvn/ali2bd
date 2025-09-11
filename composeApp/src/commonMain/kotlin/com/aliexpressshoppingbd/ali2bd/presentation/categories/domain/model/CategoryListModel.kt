package com.aliexpressshoppingbd.ali2bd.presentation.categories.domain.model

data class CategoryListModel(
    val id: Int,
    val name: String
) {
    companion object {
        val sampleCategories = listOf(
            CategoryListModel(1, "For You"),
            CategoryListModel(2, "Furniture"),
            CategoryListModel(3, "Home & Garden"),
            CategoryListModel(4, "Men’s Clothing"),
            CategoryListModel(5, "Electronics"),
            CategoryListModel(6, "Beauty"),
            CategoryListModel(7, "Sports"),
            CategoryListModel(8, "Toys"),
            CategoryListModel(9, "Automotive")
        )
    }
}

