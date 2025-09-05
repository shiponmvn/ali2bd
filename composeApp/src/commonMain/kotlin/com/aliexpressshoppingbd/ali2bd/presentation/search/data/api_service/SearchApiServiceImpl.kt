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
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom

class SearchApiServiceImpl(
    private val httpClient: HttpClient
) : SearchApiService {
    override suspend fun getSystemConfig(): SystemConfigResponse {
        try {
            println("SearchApiServiceImpl: Starting getSystemConfig request")
            val response = httpClient.get {
                url {
                    takeFrom("https://edge.ali2bd.com/api/public/v1/system/configs")

                }
                contentType(ContentType.Application.Json)
            }
            val res: SystemConfigResponse = response.body();
            return res;
            println("SearchApiServiceImpl: Received response with status: ${response.status}")

            try {
                val configResponse = response.body<SystemConfigResponse>()
                println("SearchApiServiceImpl: Successfully parsed response body")
                return configResponse
            } catch (e: Exception) {
                println("SearchApiServiceImpl: Error parsing response body: ${e.message}")
                throw Exception("Failed to parse system config response: ${e.message}", e)
            }
        } catch (e: Exception) {
            println("SearchApiServiceImpl: API request failed: ${e.message}")
            throw Exception("System config API request failed: ${e.message}", e)
        }
    }
}