package business.datasource.network.main


import com.aliexpressshoppingbd.ali2bd.data.network.ApiService.Companion.BASE_URL
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.api_service.SearchApiService
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormPart
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json

class SearchApiServiceImpl(
    private val httpClient: HttpClient
) : SearchApiService {
    override suspend fun getSystemConfig(): SystemConfigResponse {
        try {
            println("SearchApiServiceImpl: Starting getSystemConfig request")
            val response = HttpClient().get {
                url {
                    takeFrom("https://edge.ali2bd.com/api/public/v1/system/configs")
                }
                contentType(ContentType.Application.Json)
            }

            println("SearchApiServiceImpl: Received response with status: ${response.status}")

            // Print the raw response body for debugging
            val rawBody = response.bodyAsText()
            println("SearchApiServiceImpl: Raw response body: $rawBody")

            try {
                val configResponse: SystemConfigResponse = Json.decodeFromString(rawBody)
                println("SearchApiServiceImpl: Successfully parsed response body")
                return configResponse
            } catch (e: Exception) {
                println("SearchApiServiceImpl: Error parsing response body: ${e.message}")
                e.printStackTrace()
                throw Exception("Failed to parse system config response: ${e.message}", e)
            }
        } catch (e: Exception) {
            println("SearchApiServiceImpl: API request failed: ${e.message}")
            e.printStackTrace()
            throw Exception("System config API request failed: ${e.message}", e)
        }
    }
}