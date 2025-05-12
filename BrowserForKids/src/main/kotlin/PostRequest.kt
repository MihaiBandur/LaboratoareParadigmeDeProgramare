package org.example

import khttp.post
import khttp.responses.Response

class PostRequest(val genericReq: GenericRequest) {
    fun postData(): Response {
        return post(
            url = genericReq.url,
            data = genericReq.params ?: mapOf<String, String>()
        )
    }
}
