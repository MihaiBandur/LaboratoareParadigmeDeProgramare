package org.example

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.File
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Performs an HTTP GET request to fetch HTML content.
 * @param url The URL of the website.
 * @return The HTML content as a string.
 */
fun testHttpGetRequest(url: String): String {
    val client = OkHttpClient()
    val request = Request.Builder().url(url).build()

    client.newCall(request).execute().use { response ->
        println("${response.code}\t${response.header("Content-Type")}")
        return response.body?.string().orEmpty()
    }
}

/**
 * Parses an HTML document from a URL, file, or raw string and extracts elements.
 * @param source The type of the source ("url", "file", or "string").
 * @param url The URL, file path, or HTML string.
 * @param baseURI The base URI for relative links (optional).
 * @throws Exception If the source type is unknown.
 */
fun testJsoup(source: String, url: String, baseURI: String? = null) {
    val htmlDocument: Document = when (source) {
        "url" -> Jsoup.connect(url).get()
        "file" -> Jsoup.parse(File(url), "UTF-8", baseURI)
        "string" -> Jsoup.parse(url)
        else -> throw Exception("Unknown source type: $source")
    }

    val cssHeadlineSelector = "#khttp-http-without-the-bullshit h1"
    val cssParagraphSelector = "#khttp-http-without-the-bullshit p"
    val cssLinkSelector = "#khttp-http-without-the-bullshit > p > a"

    println(htmlDocument.title())
    println(htmlDocument.select(cssHeadlineSelector).text())

    val paragraphs: Elements = htmlDocument.select(cssParagraphSelector)
    for (paragraph in paragraphs) {
        println("\t${paragraph.text()}")
    }

    val links = htmlDocument.select(cssLinkSelector)
    println("-".repeat(100))

    for (link in links) {
        println("${link.text()}\n\t${link.absUrl("href")}")
    }
}

fun main() {
    val projectPath = System.getProperty("user.dir")
    val htmlPath = "$projectPath/src/main/resources/example.html"
    val url = "https://khttp.readthedocs.io/en/latest/"

    val htmlContent = testHttpGetRequest(url)
    println("*".repeat(100))

    testJsoup("url", url)
    println("*".repeat(100))

    testJsoup("file", htmlPath, "mike.tuiasi.ro")
    println("*".repeat(100))

    testJsoup("string", htmlContent)
}
