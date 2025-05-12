package org.example

class GenericRequest(var url: String, var params: Map<String, String>? = null): Cloneable {
    override fun clone(): GenericRequest {
        return GenericRequest(url = this.url, params = this.params?.toMap())
    }
}