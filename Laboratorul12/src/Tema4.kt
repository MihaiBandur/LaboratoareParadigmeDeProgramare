class FunctorMap(private  val map: MutableMap<Int, String>)
{
    fun map(f: (String) -> String): FunctorMap {
        val rezultat = map.mapValues { (_, value) -> f(value) }.toMutableMap()
        return  FunctorMap(rezultat)
    }

    fun get(): MutableMap<Int, String> = map
}

fun String.toPascalCase(): String {
    return this.split(" ", "\t")
        .filter { it.isNotBlank() }
        .joinToString(" ") {it.lowercase().replaceFirstChar { c ->  c.uppercase() }  }
}

fun main() {
    val initialMap = mutableMapOf(
        1 to "salut lume",
        2 to "functor map test",
        3 to "kotlin este cool"
    )

    val rezultat = FunctorMap(initialMap)
        .map { "Test $it" }
        .map { it.toPascalCase() }
        .get()

    println("Rezultat final:")
    rezultat.forEach { (k, v) -> println("$k: $v") }
}