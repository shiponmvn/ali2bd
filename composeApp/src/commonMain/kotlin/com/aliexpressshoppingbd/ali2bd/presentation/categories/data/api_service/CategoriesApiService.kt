package com.aliexpressshoppingbd.ali2bd.presentation.categories.data.api_service

import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoriesResponse
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryProductsResponse

interface CategoriesApiService {
    suspend fun getCategories(): CategoriesResponse
    suspend fun getCategoryProducts(categoryId: String): CategoryProductsResponse
}
