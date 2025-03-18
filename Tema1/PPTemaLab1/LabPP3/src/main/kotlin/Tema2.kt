package org.example
import java.io.File
import java.util.regex.Pattern
import org.jsoup.Jsoup

fun main(){
    val citire="/Users/bandu/Desktop/ebook.txt"
    val scriere="/Users/bandu/Desktop/ebookout.txt"
    val text=File(citire).readText()
    val textprocesat=functieProcesare(text)
    File(scriere).writeText(textprocesat)

}
fun functieProcesare(text : String): String {
    var textProcesat=text

    textProcesat=textProcesat.replace(Regex("\\s+")," ")


    textProcesat=textProcesat.replace(Regex("\\n+"),"\n")
    textProcesat=textProcesat.replace(Regex("Pagina \\d+"), "")
    return textProcesat.trim()

}