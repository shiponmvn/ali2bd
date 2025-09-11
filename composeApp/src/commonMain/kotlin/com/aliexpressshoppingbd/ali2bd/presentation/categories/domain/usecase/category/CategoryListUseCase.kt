package com.aliexpressshoppingbd.ali2bd.presentation.categories.domain.usecase.category

import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.api_service.CategoriesApiService
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryItem
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.ProductItem

class CategoryListUseCase(
    private val categoriesApiService: CategoriesApiService
) {
    suspend fun getCategories(): Result<List<CategoryItem>> {
        return try {
            val response = categoriesApiService.getCategories()
            Result.success(response.categories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCategoryProducts(categoryId: String): Result<List<ProductItem>> {
        return try {
            val response = categoriesApiService.getCategoryProducts(categoryId)
            Result.success(response.products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
