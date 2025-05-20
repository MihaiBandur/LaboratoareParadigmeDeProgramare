fun String.count():String{
    return this.groupingBy{it}.eachCount().entries.joinToString(""){if(it.value > 1) {"${it.key}${it.value}"}else {"${it.key}"} }
}

fun main() {
    val a = "aaaabbbccda"
    println("Sirul initial este $a, iar dupa apel devine ${a.count()}")
}