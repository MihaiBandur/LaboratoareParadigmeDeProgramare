package org.example

import khttp.get
import khttp.post
import khttp.responses.Response

class KidsBrowser(private val cleanGet: CleanGetRequest, private val postRequest: PostRequest?)
{
    fun start()
    {
        println("Kids Browser started")
        println("Opening safe home page ... ")
        try {
            val homePageResponse = cleanGet.getResponse()
            println("Home page loadede with status:${homePageResponse.statusCode}")
            println("Page title ${extractTitle(homePageResponse.text)}")

            println("\nTrying to access a social media site... ")
            val originalUrl = cleanGet.getRequest.genericReq.url
            cleanGet.getRequest.genericReq.url = "https://www.facebook.com"
            val blockedResponse = cleanGet.getResponse()
            println("Responsed received with status ${blockedResponse.statusCode}")

            if(postRequest != null)
            {
                println("\nSubmiting a search query...")
                postRequest.genericReq.url = "https://www.kidssearch.com/search"
                postRequest.genericReq.params = mapOf("q" to "Dinosaursus", "safe" to "true")
                val searchResponse = postRequest.postData()
                println("Search submitted with status: ${searchResponse.statusCode}")
            }
            cleanGet.getRequest.genericReq.url = originalUrl

        }
        catch (e: Exception){
            println("Error during browsing: ${e.message}")
        }
        println("\n Kids Browser is ready to use\n")
    }

    private fun extractTitle(html: String): String {
        val titleRegex = "<title>(.*?)</title>".toRegex(RegexOption.IGNORE_CASE)
        val matchResult = titleRegex.find(html)
        return matchResult?.groupValues?.get(1) ?: "No title found"
    }

}