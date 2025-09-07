package com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.res

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    @SerialName("items")
    val items: List<ProductItem>
)

@Serializable
data class ProductItem(
    @SerialName("id")
    val id: String? = null,
    @SerialName("vpid")
    val vpid: Long,
    @SerialName("vendor")
    val vendor: String,
    @SerialName("title")
    val title: String,
    @SerialName("link")
    val link: String,
    @SerialName("image")
    val image: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("sold")
    val sold: Int,
    @SerialName("stock")
    val stock: Int? = null,
    @SerialName("rating")
    val rating: String,
    @SerialName("rating_count")
    val rating_count: Int? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("countdown")
    val countdown: String? = null,
    @SerialName("badge")
    val badge: String? = null,
    @SerialName("price")
    val price: ProductPrice,
    @SerialName("meta")
    val meta: ProductMeta,
    @SerialName("discount_price")
    val discount_price: ProductDiscountPrice,
    @SerialName("wholesales")
    val wholesales: List<String> = emptyList(),
    @SerialName("shipping_cost")
    val shipping_cost: String? = null,
    @SerialName("slug")
    val slug: String,
    @SerialName("product_code")
    val product_code: String,
    @SerialName("country_id")
    val country_id: Int,
    @SerialName("shop_id")
    val shop_id: Int,
    @SerialName("fx")
    val fx: String,
    @SerialName("delivery_charge")
    val delivery_charge: DeliveryCharge
)

@Serializable
data class ProductPrice(
    @SerialName("min")
    val min: String,
    @SerialName("max")
    val max: String
)

@Serializable
data class ProductMeta(
    @SerialName("freight")
    val freight: List<String> = emptyList()
)

@Serializable
data class ProductDiscountPrice(
    @SerialName("min")
    val min: String? = null,
    @SerialName("max")
    val max: String? = null
)

@Serializable
data class DeliveryCharge(
    @SerialName("percent")
    val percent: Int,
    @SerialName("type")
    val type: String
)
