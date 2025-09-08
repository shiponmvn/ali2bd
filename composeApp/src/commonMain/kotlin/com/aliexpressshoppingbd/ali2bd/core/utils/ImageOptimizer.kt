package com.aliexpressshoppingbd.ali2bd.core.utils

/**
 * Singleton class for optimizing image URLs based on size and store
 */
object ImageOptimizer {

    // Map to define image sizes
    private val SIZES = mapOf(
        "xs" to mapOf("width" to 60, "height" to 60),
        "sm" to mapOf("width" to 100, "height" to 100),
        "md" to mapOf("width" to 300, "height" to 300),
        "lg" to mapOf("width" to 300, "height" to 300),
        "xl" to mapOf("width" to 400, "height" to 400),
        "xxl" to mapOf("width" to 500, "height" to 500),
        "xxxl" to mapOf("width" to 600, "height" to 600)
    )

    /**
     * Detect store based on the URL
     */
    private fun detectStore(src: String): String {
        return when {
            src.contains("alicdn.com") || src.contains("1688.com") -> "alicdn"
            src.contains("amazon.com") -> "amazon"
            src.contains("flipkart.com") || src.contains("rukminim2.flixcart.com") -> "flipkart"
            else -> "unknown"
        }
    }

    /**
     * Generate optimized URL based on size and store
     */
    fun generateOptimizedUrl(src: String, size: String?): String {
        val quality = 100
        var width = 0
        var height = 0

        if (size != null && SIZES.containsKey(size)) {
            val selectedSize = SIZES[size]!!
            width = selectedSize["width"]!!
            height = selectedSize["height"]!!
        } else {
            return src // No optimization if no valid size is given
        }

        var optimizedImage = src
        val store = detectStore(src)

        return when (store) {
            "alicdn" -> {
                val images = src.split(".")
                if (images.isNotEmpty() && images.last() == "jpg") {
                    optimizedImage = src.replace(".jpg", ".jpg_${width}x${height}q$quality.jpg")
                    optimizedImage
                } else {
                    src // Return the original image if not a .jpg
                }
            }
            "amazon" -> src // No optimization for Amazon
            "flipkart" -> src // No optimization for Flipkart
            else -> src // Default case for unknown sources
        }
    }

    /**
     * Convenience method to get optimized image URL with common sizes
     */
    fun getOptimizedUrl(src: String, size: ImageSize = ImageSize.MD): String {
        return generateOptimizedUrl(src, size.value)
    }
}

/**
 * Enum class for predefined image sizes
 */
enum class ImageSize(val value: String) {
    XS("xs"),
    SM("sm"),
    MD("md"),
    LG("lg"),
    XL("xl"),
    XXL("xxl"),
    XXXL("xxxl")
}
