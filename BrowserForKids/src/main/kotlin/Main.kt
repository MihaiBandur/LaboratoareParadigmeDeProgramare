package org.example

fun main() {
    val prototypeRequest = GenericRequest(url = "https://kids.nationalgeographic.com/")
    val timeouts = mapOf("connect" to "3000", "read" to "5000")
    val getRequest = GetRequest(timeout = timeouts, genericReq = prototypeRequest.clone())
    val blockedDomains = listOf(
        "facebook.com",
        "twitter.com",
        "instagram.com",
        "tiktok.com",
        "adult-content.com"
    )
    val cleanGetRequest = CleanGetRequest(getRequest = getRequest, parentalControlDisallow = blockedDomains)
    val postRequest = PostRequest(genericReq = prototypeRequest.clone())
    val kidsBrowser = KidsBrowser(cleanGet = cleanGetRequest, postRequest = postRequest)
    kidsBrowser.start()
}
