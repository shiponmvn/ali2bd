package com.aliexpressshoppingbd.ali2bd.presentation.cart.data.api_service


import com.aliexpressshoppingbd.ali2bd.presentation.cart.data.res.CartResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.delete
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CartApiServiceImpl(
    private val httpClient: HttpClient
) : CartApiService {

    private val cartEndpoint = "https://edge.ali2bd.com/api/consumer/v1/cart/items"

    override suspend fun getCartItems(): CartResponse {
        val token = "" // or any other source

        return httpClient.get(cartEndpoint) {
            header("Authorization", "Bearer 457361|BbFBOhAV7KAljlwLJbE8ko6Uoc8iKpBDZ1jn6Lae")
        }.body()
    }


    override suspend fun addToCart(productId: String, quantity: Int, attributes: Map<String, String>?): CartResponse {
        return httpClient.post(cartEndpoint) {
            contentType(ContentType.Application.Json)
            setBody(mapOf(
                "product_id" to productId,
                "quantity" to quantity,
                "attributes" to attributes
            ))
        }.body()
    }

    override suspend fun updateCartItem(cartItemId: String, quantity: Int): CartResponse {
        return httpClient.put("$cartEndpoint/$cartItemId") {
            contentType(ContentType.Application.Json)
            setBody(mapOf(
                "quantity" to quantity
            ))
        }.body()
    }

    override suspend fun removeFromCart(cartItemId: String): CartResponse {
        return httpClient.delete("$cartEndpoint/$cartItemId").body()
    }
}
