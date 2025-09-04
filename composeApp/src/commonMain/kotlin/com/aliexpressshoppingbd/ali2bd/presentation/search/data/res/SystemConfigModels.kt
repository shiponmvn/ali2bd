package com.aliexpressshoppingbd.ali2bd.presentation.search.data.res

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class SystemConfigResponse(
    val status: String,
    val data: List<SystemConfigItem>
)

@Serializable
data class ErrorResponse(
    val status: Int,
    val message: String
)

@Serializable
data class SystemConfigItem(
    val id: Int,
    val name: String,
    val description: String? = null,
    val group: String,
    val type: String? = null,
    val key: String,
    val value: JsonElement  // Using JsonElement to handle different value types
)

// Helper classes for parsing specific config item types
@Serializable
data class ConfigObject(
    val key: String? = null,
    val label: String? = null,
    val image: String? = null,
    val fileId: String? = null,
    val link: String? = null
)
