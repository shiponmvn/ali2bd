package com.aliexpressshoppingbd.ali2bd.presentation.categories.data.api_service

import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryApiResponse
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryProductsResponse
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.ProductItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.delay

class CategoriesApiServiceImpl(
    private val httpClient: HttpClient
) : CategoriesApiService {

    private val categoryApiEndPoint = "https://edge.ali2bd.com/api/public/v1/system/menus"

    override suspend fun getCategories(): CategoryApiResponse {
        return httpClient.get (categoryApiEndPoint) {
            contentType(ContentType.Application.Json)
        }.body()

    }

}
