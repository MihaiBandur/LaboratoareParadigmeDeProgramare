package org.example
import khttp.get
import khttp.responses.Response

class GetRequest(
    private val timeout: Map<String, String>,
    val genericReq: GenericRequest
) : HTTPGet {
    override fun getResponse(): Response {
        val connectTimeout = timeout["connect"]?.toIntOrNull() ?: 5000
        val readTimeout = timeout["read"]?.toIntOrNull() ?: 5000

        return get(
            url = genericReq.url,
            params = genericReq.params ?: mapOf(),
            timeout = connectTimeout / 1000.0
        )
    }
}
