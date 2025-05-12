import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io.File

data class Note(val title: String, val content: String, val author: String) {
    val timestamp: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

    fun saveToFile() {
        val fileName = "$title.txt"
        val fileContent = "Autor: $author\nData: $timestamp\n\n$content"
        java.io.File(fileName).writeText(fileContent)
    }

    companion object {
        fun loadFromFile(title: String): Note? {
            val file = java.io.File("$title.txt")
            if (!file.exists()) return null

            val lines = file.readLines()
            val author = lines[0].removePrefix("Autor: ")
            val content = lines.drop(2).joinToString("\n")
            return Note(title, content, author)
        }
    }
}


class NoteManager {
    fun listNotes(): List<String> {
        return File(".").listFiles()?.filter { it.name.endsWith(".txt") }?.map { it.name.removeSuffix(".txt") } ?: emptyList()
    }

    fun deleteNote(title: String): Boolean {
        val file = File("$title.txt")
        return if (file.exists()) {
            file.delete()
            true
        } else {
            false
        }
    }
}

class User(val name: String) {
    private val noteManager = NoteManager()

    fun createNote(title: String, content: String) {
        val note = Note(title, content, name)
        note.saveToFile()
        println("Notița \"$title\" a fost salvată.")
    }

    fun viewNotes() {
        val notes = noteManager.listNotes()
        if (notes.isEmpty()) {
            println("Nu există notițe salvate.")
        } else {
            println("Notițe disponibile:")
            notes.forEach { println("- $it") }
        }
    }

    fun openNote(title: String) {
        val note = Note.loadFromFile(title)
        if (note != null) {
            println("Titlu: ${note.title}\nAutor: ${note.author}\nData: ${note.timestamp}\n\n${note.content}")
        } else {
            println("Notița \"$title\" nu există.")
        }
    }

    fun deleteNote(title: String) {
        if (noteManager.deleteNote(title)) {
            println("Notița \"$title\" a fost ștearsă.")
        } else {
            println("Eroare: Notița \"$title\" nu a fost găsită.")
        }
    }
}

fun main() {
    println("Bine ai venit la aplicația de notițe!")
    print("Introdu numele tău: ")
    val userName = readln()
    val user = User(userName)

    while (true) {
        println("\nMeniu:")
        println("1. Vizualizare notițe")
        println("2. Creare notiță")
        println("3. Deschidere notiță")
        println("4. Ștergere notiță")
        println("5. Ieșire")
        print("Alege o opțiune: ")

        when (readln()) {
            "1" -> user.viewNotes()
            "2" -> {
                print("Titlul notiței: ")
                val title = readln()
                print("Conținutul notiței: ")
                val content = readln()
                user.createNote(title, content)
            }
            "3" -> {
                print("Introdu titlul notiței pe care vrei să o deschizi: ")
                val title = readln()
                user.openNote(title)
            }
            "4" -> {
                print("Introdu titlul notiței pe care vrei să o ștergi: ")
                val title = readln()
                user.deleteNote(title)
            }
            "5" -> {
                println("La revedere!")
                break
            }
            else -> println("Opțiune invalidă. Încearcă din nou.")
        }
    }
}
