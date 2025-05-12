package org.example

import khttp.get
import khttp.responses.Response



class CleanGetRequest(val getRequest: GetRequest, private  val parentalControlDisallow: List<String>): HTTPGet {
    override fun getResponse(): Response {
        val url = getRequest.genericReq.url
        for(disallowedDomain in parentalControlDisallow){
            if(url.contains(disallowedDomain, ignoreCase = true))
            {
                println("Access to $url is blocked by parental controls")
                return get("https://www.safe-for-kids.com/blocked.html")
            }
        }
        return getRequest.getResponse()
    }
}