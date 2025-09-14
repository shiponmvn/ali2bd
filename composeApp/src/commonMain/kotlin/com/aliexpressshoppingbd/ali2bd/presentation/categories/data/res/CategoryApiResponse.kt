package com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive


object StringOrEmptySerializer : KSerializer<String> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("StringOrEmpty", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: String) {
        encoder.encodeString(value)
    }

    override fun deserialize(decoder: Decoder): String {
        // If the value is null, return empty string
        return try {
            (decoder as? JsonDecoder)?.decodeJsonElement()?.jsonPrimitive?.contentOrNull ?: ""
        } catch (e: Exception) {
            ""
        }
    }
}
@Serializable
data class CategoryApiResponse(
    @SerialName("status") val status: String = "",
    @SerialName("message") val message: String = "",
    @SerialName("data") val data: CategoryResponseData = CategoryResponseData()
)

@Serializable
data class CategoryResponseData(
    @SerialName("app_drawer")
    val appDrawer: AppDrawer = AppDrawer()
)

@Serializable
data class AppDrawer(
    @SerialName("name") val name: String = "",
    @SerialName("items") val items: List<CategoryMenuItem> = emptyList()
)

@Serializable
data class CategoryMenuItem(
    @SerialName("id") val id: Int = 0,
    @SerialName("title") val title: String = "",
    @SerialName("type") val type: String = "",
    @SerialName("value") val value: String = "",
    @SerialName("icon") val icon: String = "",
    @SerialName("image") val image: String? = "",
    @SerialName("children") val children: List<CategoryMenuItem> = emptyList()
)
