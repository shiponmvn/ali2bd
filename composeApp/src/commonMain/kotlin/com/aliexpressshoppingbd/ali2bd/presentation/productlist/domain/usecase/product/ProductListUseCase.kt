package com.aliexpressshoppingbd.ali2bd.presentation.productlist.domain.usecase.product

import com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.api_service.ProductListApiService
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.res.ProductListResponse
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.domain.model.ProductListModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.ErrorResponse
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductListUseCase(
    private val productListApiService: ProductListApiService
) {
    suspend fun searchProducts(keyword: String): Flow<Result<ProductListResponse>> = flow {
        try {
            val response = productListApiService.searchProducts(keyword = keyword)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(Exception("Unknown error occurred")))
        }
    }



}
