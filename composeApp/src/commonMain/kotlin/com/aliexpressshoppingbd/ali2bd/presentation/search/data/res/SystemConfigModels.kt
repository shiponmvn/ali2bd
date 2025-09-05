package com.aliexpressshoppingbd.ali2bd.presentation.search.data.res

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class SystemConfigResponse(
    @SerialName("status") val status: String,
    @SerialName("data") val data: List<SystemConfigItem> = listOf()
)

@Serializable
data class ErrorResponse(
    @SerialName("status") val status: Int,
    @SerialName("message") val message: String
)

@Serializable
data class SystemConfigItem(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null,
    @SerialName("group") val group: String,
    @SerialName("type") val type: String? = null,
    @SerialName("key") val key: String,
    @SerialName("value") val value: List<ValueData>? = listOf() // Using JsonElement to handle different value types and making it nullable
)

@Serializable
data class ValueData(
    @SerialName("key") val key: String? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("fileId") val fileId: String? = null
)

// Helper classes for parsing specific config item types
@Serializable
data class ConfigObject(
    @SerialName("key") val key: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("fileId") val fileId: String? = null,
    @SerialName("link") val link: String? = null
)
