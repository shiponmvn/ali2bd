package business.datasource.network.main


import com.aliexpressshoppingbd.ali2bd.presentation.search.data.api_service.SearchApiService
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
class SearchApiServiceImpl(
    private val httpClient: HttpClient
) : SearchApiService {
    override suspend fun getSystemConfig(): SystemConfigResponse {

        try {
            val response = httpClient.get {
                url {
                    takeFrom("https://edge.ali2bd.com/api/public/v1/system/configs")
                }
            }
            val rawBody = response.bodyAsText()
            val configResponse: SystemConfigResponse = Json.decodeFromString(rawBody)
            return configResponse

        } catch (e: Exception) {
            throw  e;

        }
    }
}