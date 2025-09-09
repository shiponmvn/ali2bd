package com.aliexpressshoppingbd.ali2bd.presentation.cart.domain.usecase.cart

import com.aliexpressshoppingbd.ali2bd.presentation.cart.data.api_service.CartApiService
import com.aliexpressshoppingbd.ali2bd.presentation.cart.data.res.CartResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CartUseCase(
    private val cartApiService: CartApiService
) {
    suspend fun getCartItems(): Flow<Result<CartResponse>> = flow {
        try {
            val response = cartApiService.getCartItems()
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(Exception("Unknown error occurred")))
        }
    }

    suspend fun addToCart(productId: String, quantity: Int, attributes: Map<String, String>? = null): Flow<Result<CartResponse>> = flow {
        try {
            val response = cartApiService.addToCart(productId, quantity, attributes)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(Exception("Unknown error occurred")))
        }
    }

    suspend fun updateCartItem(cartItemId: String, quantity: Int): Flow<Result<CartResponse>> = flow {
        try {
            val response = cartApiService.updateCartItem(cartItemId, quantity)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(Exception("Unknown error occurred")))
        }
    }

    suspend fun removeFromCart(cartItemId: String): Flow<Result<CartResponse>> = flow {
        try {
            val response = cartApiService.removeFromCart(cartItemId)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(Exception("Unknown error occurred")))
        }
    }
}
