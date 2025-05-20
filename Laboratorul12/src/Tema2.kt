import java.io.File

fun caesarEncrypt(word: String, offset: Int): String {
    return word.map {
        when {
            it.isUpperCase() -> 'A' + (it - 'A' + offset) % 26
            it.isLowerCase() -> 'a' + (it - 'a' + offset) % 26
            else -> it
        }
    }.joinToString("")
}

fun main() {
    print("Introduceți calea către fișier: ")
    val filePath = readLine() ?: return

    print("Introduceți offsetul pentru cifrul Caesar: ")
    val offset = readLine()?.toIntOrNull() ?: return println("Offset invalid.")

    val text = File(filePath).readText()

    val rezultat = text.split(Regex("\\s+")).joinToString(" ") { cuvânt ->
        if (cuvânt.length in 4..7)
            caesarEncrypt(cuvânt, offset)
        else
            cuvânt
    }

    println("\nText procesat:\n$rezultat")
}