package com.aliexpressshoppingbd.ali2bd.presentation.search.data.res

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class SystemConfigResponse(
    @SerialName("status") val status: String,
    @SerialName("data") val data: List<SystemConfigItem> = listOf()
)

@Serializable
data class ErrorResponse(
    @SerialName("message") override val message: String?
) : Throwable(message)

@Serializable
data class SystemConfigItem(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null,
    @SerialName("group") val group: String,
    @SerialName("type") val type: String? = null,
    @SerialName("key") val key: String,
    @SerialName("value") val rawValue: JsonElement? = null // Using JsonElement to handle dynamic types
)

@Serializable
data class ValueData(
    @SerialName("key") val key: String? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("fileId") val fileId: String? = null
)

