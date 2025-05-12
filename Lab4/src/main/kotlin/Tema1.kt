import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.w3c.dom.Element
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import org.xml.sax.InputSource


abstract class Parser {
    abstract fun parseQuest(response: String): Map<String, Any>
}


class Application {
    private val client = OkHttpClient()

    fun getResource(url: String, parser: Parser): Map<String, Any> {
        val request = Request.Builder().url(url).build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw RuntimeException("Eroare HTTP: ${response.code}")
            val responseBody = response.body?.string() ?: throw RuntimeException("Răspuns gol")
            return parser.parseQuest(responseBody)
        }
    }
}


class JsonParser : Parser() {
    override fun parseQuest(response: String): Map<String, Any> {
        val jsonObject = JSONObject(response)
        return jsonObject.toMap()
    }

    private fun JSONObject.toMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        for (key in this.keys()) {
            val value = this.get(key)
            map[key] = when (value) {
                is JSONObject -> value.toMap()
                is org.json.JSONArray -> value.toList()
                else -> value
            }
        }
        return map
    }

    private fun org.json.JSONArray.toList(): List<Any> {
        val list = mutableListOf<Any>()
        for (i in 0 until this.length()) {
            val value = this.get(i)
            list.add(
                when (value) {
                    is JSONObject -> value.toMap()
                    is org.json.JSONArray -> value.toList()
                    else -> value
                }
            )
        }
        return list
    }
}


class XmlParser : Parser() {
    override fun parseQuest(response: String): Map<String, Any> {
        val docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc = docBuilder.parse(InputSource(StringReader(response)))

        val root = doc.documentElement
        return parseElement(root)
    }

    private fun parseElement(element: Element): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        val nodeList = element.childNodes

        for (i in 0 until nodeList.length) {
            val node = nodeList.item(i)
            if (node is Element) {
                map[node.tagName] = node.textContent.trim()
            }
        }
        return map
    }
}


class YamlParser : Parser() {
    override fun parseQuest(response: String): Map<String, Any> {
        val yaml = Yaml()
        return yaml.load(StringReader(response)) as Map<String, Any>
    }
}


fun main() {
    val app = Application()

    // Test pentru JSON
    val jsonParser = JsonParser()
    val jsonResponse = app.getResource("https://jsonplaceholder.typicode.com/posts/1", jsonParser)
    println("JSON Response: $jsonResponse")


    val xmlParser = XmlParser()
    val xmlUrl = "https://www.w3schools.com/xml/note.xml"  // Un exemplu valid
    val xmlResponse = app.getResource(xmlUrl, xmlParser)
    println("XML Response: $xmlResponse")


    val yamlParser = YamlParser()
    val yamlFile = File("data.yaml")
    if (yamlFile.exists()) {
        val yamlResponse = yamlParser.parseQuest(yamlFile.readText())
        println("YAML Response: $yamlResponse")
    } else {
        println("Fișierul YAML nu există.")
    }
}
