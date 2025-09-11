package com.aliexpressshoppingbd.ali2bd.presentation.categories.data.api_service

import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoriesResponse
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryItem
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryProductsResponse
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.ProductItem
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay

class CategoriesApiServiceImpl(
    private val client: HttpClient
) : CategoriesApiService {
    override suspend fun getCategories(): CategoriesResponse {
        // Simulating API call with mock data
        delay(500)
        return CategoriesResponse(
            categories = listOf(
                "For You",
                "Furniture",
                "Home & Garden",
                "Men's Clothing",
                "Luggage, Bags & Shoes",
                "Sports & Entertainment",
                "Jewelry, Watches & Accessories",
                "Home Improvement & Lighting",
                "Automotive & Motorcycle",
                "Women's Clothing",
                "Beauty & Health",
                "Electronics"
            ).map { CategoryItem(it, it.lowercase().replace(" & ", "-").replace("'s ", "-").replace(", ", "-")) }
        )
    }

    override suspend fun getCategoryProducts(categoryId: String): CategoryProductsResponse {
        // Simulating API call with mock data
        delay(500)
        return CategoryProductsResponse(
            products = List(30) { index ->
                ProductItem(
                    id = "product_$categoryId$index",
                    title = "Product ${index + 1}",
                    imageUrl = "https://picsum.photos/300/300?random=$index",
                    price = "$${(10..100).random()}.99"
                )
            }
        )
    }
}
