package com.aliexpressshoppingbd.ali2bd.presentation.categories.domain.usecase.category

import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.api_service.CategoriesApiService
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryMenuItem

class CategoryListUseCase(
    private val categoriesApiService: CategoriesApiService
) {
    suspend fun getCategories(): Result<List<CategoryMenuItem>> {
        return try {
            val response = categoriesApiService.getCategories()
            Result.success(response.data.appDrawer.items)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
