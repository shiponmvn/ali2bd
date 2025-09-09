package com.aliexpressshoppingbd.ali2bd.presentation.cart.data.res

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartResponse(
    @SerialName("data") val data: List<CartItem> = emptyList(),
    @SerialName("status") val status: String = "",
    @SerialName("message") val message: String = "",
    @SerialName("charge_type") val chargeType: String = ""
)

@Serializable
data class CartItem(
    @SerialName("id") val id: Long = 0L,
    @SerialName("shop_id") val shopId: Int = 0,
    @SerialName("seller_id") val sellerId: String = "",
    @SerialName("seller_name") val sellerName: String = "",
    @SerialName("order_type") val orderType: String = "",
    @SerialName("vpid") val vpid: String = "",
    @SerialName("inv_pid") val invPid: Long = 0L,
    @SerialName("title") val title: String = "",
    @SerialName("link") val link: String = "",
    @SerialName("image") val image: String = "",
    @SerialName("quantity") val quantity: Int = 0,
    @SerialName("price") val price: Double = 0.0,
    @SerialName("attrs") val attrs: String? = null,
    @SerialName("skuid") val skuId: String? = null,
    @SerialName("freight") val freight: String = "",
    @SerialName("freight_std_fee") val freightStdFee: String = "",
    @SerialName("freight_dst_fee") val freightDstFee: String = "",
    @SerialName("domestic_delivery_charge") val domesticDeliveryCharge: String = "",
    @SerialName("freight_ext_fee") val freightExtFee: String = "",
    @SerialName("instructions") val instructions: String? = null,
    @SerialName("status") val status: String = "",
    @SerialName("rejection_reason") val rejectionReason: String? = null,
    @SerialName("reason_text") val reasonText: String? = null,
    @SerialName("type") val type: String = "",
    @SerialName("added_at") val addedAt: String = "",
    @SerialName("campaign_product_id") val campaignProductId: String? = null,
    @SerialName("campaign") val campaign: String? = null,
    @SerialName("shipping") val shipping: Shipping = Shipping(),
    @SerialName("product_code") val productCode: String = "",
    @SerialName("shop") val shop: Shop = Shop(),
    @SerialName("meta_items") val metaItems: List<MetaItem> = emptyList(),
    @SerialName("approval_type") val approvalType: String? = null
)



@Serializable
data class Weight(
    @SerialName("type") val type: String = "",
    @SerialName("value") val value: Double = 0.0
)

@Serializable
data class Shipping(
    @SerialName("id") val id: Long = 0L,
    @SerialName("shipping_category_id") val shippingCategoryId: Int = 0,
    @SerialName("foreign_type") val foreignType: String = "",
    @SerialName("type") val type: String = "",
    @SerialName("unit") val unit: String = "",
    @SerialName("price") val price: String = "",
    @SerialName("slots") val slots: List<Slot> = emptyList(),
    @SerialName("category") val category: ShippingCategory? = null,
)

@Serializable
data class Slot(
    @SerialName("to") val to: String = "",
    @SerialName("from") val from: String = "",
    @SerialName("rate") val rate: Int = 0
)

@Serializable
data class ShippingCategory(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = ""
)

@Serializable
data class Shop(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("country_id") val countryId: Int = 0,
    @SerialName("identifier") val identifier: String = ""
)

@Serializable
data class MetaItem(
    @SerialName("id") val id: Long = 0L,
    @SerialName("cart_item_id") val cartItemId: Long = 0L,
    @SerialName("skuid") val skuId: String = "",
    @SerialName("props") val props: String = "",
    @SerialName("attrs") val attrs: List<Attribute> = emptyList(),
    @SerialName("quantity") val quantity: Int = 0,
    @SerialName("price") val price: Double = 0.0
)

@Serializable
data class Attribute(
    @SerialName("key") val key: String = "",
    @SerialName("value") val value: String = "",
    @SerialName("propId") val propId: String = "",
    @SerialName("valueId") val valueId: String = "",
    @SerialName("image") val image: String? = null
)
