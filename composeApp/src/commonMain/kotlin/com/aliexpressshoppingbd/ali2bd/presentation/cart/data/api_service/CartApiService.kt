package com.aliexpressshoppingbd.ali2bd.presentation.cart.data.api_service

import com.aliexpressshoppingbd.ali2bd.presentation.cart.data.res.CartResponse

interface CartApiService {
    suspend fun getCartItems(): CartResponse
    suspend fun addToCart(productId: String, quantity: Int, attributes: Map<String, String>? = null): CartResponse
    suspend fun updateCartItem(cartItemId: String, quantity: Int): CartResponse
    suspend fun removeFromCart(cartItemId: String): CartResponse
}
