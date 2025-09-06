package com.aliexpressshoppingbd.ali2bd.presentation.search.data.res

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Extension functions for JsonElement to handle dynamic data types
 */

/**
 * Converts a JsonElement to a List of ValueData objects if it's a JsonArray,
 * otherwise returns an empty list
 */
fun JsonElement?.getValueAsList(): List<ValueData> {
    if (this == null) return emptyList()

    return try {
        when (this) {
            is JsonArray -> {
                val json = Json { ignoreUnknownKeys = true }
                this.jsonArray.map { json.decodeFromJsonElement<ValueData>(it) }
            }
            else -> emptyList()
        }
    } catch (e: Exception) {
        println("Error converting to ValueData list: ${e.message}")
        emptyList()
    }
}

/**
 * Converts a JsonElement to a single ValueData object if it's a JsonObject,
 * otherwise returns null
 */
fun JsonElement?.getValueAsObject(): ValueData? {
    if (this == null) return null

    return try {
        when (this) {
            is JsonObject -> {
                val json = Json { ignoreUnknownKeys = true }
                json.decodeFromJsonElement<ValueData>(this)
            }
            else -> null
        }
    } catch (e: Exception) {
        println("Error converting to ValueData object: ${e.message}")
        null
    }
}

/**
 * Extracts a string value if the JsonElement is a JsonPrimitive,
 * otherwise returns null
 */
fun JsonElement?.getValueAsString(): String? {
    if (this == null) return null

    return try {
        when (this) {
            is JsonPrimitive -> this.jsonPrimitive.content
            else -> null
        }
    } catch (e: Exception) {
        println("Error converting to String: ${e.message}")
        null
    }
}

/**
 * Dynamically gets the most appropriate value type
 */
fun JsonElement?.getValueAsDynamic(): Any? {
    return this.getValueAsList().takeIf { it.isNotEmpty() }
        ?: this.getValueAsObject()
        ?: this.getValueAsString()
        ?: this
}
