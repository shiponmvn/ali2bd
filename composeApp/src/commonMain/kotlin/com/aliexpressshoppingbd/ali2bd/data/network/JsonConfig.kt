package com.aliexpressshoppingbd.ali2bd.data.network

import kotlinx.serialization.json.Json

object JsonConfig {
    val customJson = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true // Handle null values for non-null fields
        explicitNulls = false
        encodeDefaults = true
        classDiscriminator = "#class"
    }
}
