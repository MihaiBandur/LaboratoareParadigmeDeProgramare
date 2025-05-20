fun String.remove(): String
{
    return this.toHashSet().joinToString (" ")
}
fun main()
{
    val a = "bbaccccfffffaaa"
    println("$a dupa apel sirul devine ${a.remove()}")
}
