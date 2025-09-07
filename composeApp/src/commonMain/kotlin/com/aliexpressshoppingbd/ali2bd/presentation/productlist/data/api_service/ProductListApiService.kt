package com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.api_service

import com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.res.ProductListResponse

interface ProductListApiService {
    suspend fun searchProducts(keyword: String): ProductListResponse
}
