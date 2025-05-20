import java.text.SimpleDateFormat
import java.util.Date

fun String.toDate(format: String): Date
{
    return SimpleDateFormat(format).parse(this)
}

fun main()
{
    println("2024-05-14".toDate("yyyy-MM-dd"))
}