import java.util.*

fun <T> transform(t: T, fn: (T) -> T): T {
    return fn(t)
}

fun reverse(str: String): String {
    return str.reversed()
}

class Transformer {
    fun upperCased(str: String): String {
        return str.uppercase(Locale.getDefault())
    }
    companion object {
        fun lowerCased(str: String): String {
            return str.lowercase(Locale.getDefault())
        }
    }
}
fun main()
{

    println(transform("kotlin", {str: String -> str.capitalize()}))
    println(transform("kotlin", {it.capitalize()}))
    println(transform("kotlin", ::reverse))

}