package com.aliexpressshoppingbd.ali2bd.presentation.categories.data.api_service

import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryApiResponse
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryProductsResponse

interface CategoriesApiService {
    suspend fun getCategories(): CategoryApiResponse
}
