package org.example
import org.jsoup.Jsoup
fun main(){
    val url = "http://rss.cnn.com/rss/edition.rss"
    try {
        val site = Jsoup.connect(url).get();
        val items = site.select("item");

        for (item in items){
            val title = item.selectFirst("title").text();
            val link = item.selectFirst("link").text();

            println("Title: $title");
            println("Link: $link");
            println(" ");
        }
    }catch (e:Exception){
        println("Eroare la citirea RSS-ului");
    }
}