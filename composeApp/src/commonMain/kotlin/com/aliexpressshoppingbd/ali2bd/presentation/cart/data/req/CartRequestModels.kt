package com.aliexpressshoppingbd.ali2bd.presentation.cart.data.req

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequest(
    @SerialName("product_id") val productId: String,
    @SerialName("quantity") val quantity: Int,
    @SerialName("attributes") val attributes: Map<String, String>? = null
)

@Serializable
data class UpdateCartRequest(
    @SerialName("quantity") val quantity: Int
)
