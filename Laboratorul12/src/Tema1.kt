fun MutableList<Int>.remove()
{
    this.removeAll{it < 5}
}

fun MutableList<Int>.group(): List<List<Int>>
{
    return this.windowed(2, 2)
}

fun List<List<Int>>.product():List<Int>{
    return this.map { it[0] * it[1] }
}

fun main() {
    var list = mutableListOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)
    list.remove()
    println(list)
    val l2 = list.group()
    println(l2)
    val l3 = l2.product()
    println(l3)
    println(l3.sum())
    println("Hello World!")
}