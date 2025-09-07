package com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.api_service

import com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.res.ProductListResponse
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json

class ProductListApiServiceImpl(
    private val httpClient: HttpClient
) : ProductListApiService {

    override suspend fun searchProducts(keyword: String): ProductListResponse {

        try {
            return httpClient.get("https://edge.ali2bd.com/api/public/v1/products/search") {
                parameter("keyword", keyword)
            }.body()
        } catch (e: Exception) {
            throw e
        }
    }
}
