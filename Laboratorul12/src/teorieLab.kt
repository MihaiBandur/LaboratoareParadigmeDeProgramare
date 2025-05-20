//val capitalize = {str: String -> str.capitalize()}
val capitalize = object : Function1<String, String> {
    override fun invoke(p1: String): String {
        return p1.capitalize();
    }
}
fun main()
{
    println(capitalize("hello world!"))
}