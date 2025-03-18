package org.example

fun main() {
    val Dictionar = hashMapOf(
        "Once" to "Odata",
        "upon" to "ca",
        "a" to "",
        "time" to "niciodata",
        "there" to "acolo",
        "was" to "a fost",
        "an" to "o",
        "old" to "batrana",
        "woman" to "femeie",
        "who" to "care",
        "loved" to "iubea",
        "baking" to "sa gateasca",
        "gingerbread" to "turta dulce",
        "She" to "Ea",
        "would" to "ar fi",
        "bake" to "gatit",
        "cookies" to "biscuiti",
        "cakes" to "prajituri",
        "houses" to "case",
        "and" to "si",
        "people" to "oameni",
        "all" to "toti",
        "decorated" to "decorati",
        "with" to "cu",
        "chocolate" to "ciocolata",
        "peppermint" to "menta",
        "caramel" to "caramel",
        "candies" to "bomboane",
        "colored" to "colorate",
        "ingredients" to "ingrediente"
    )

    val Poveste = """
        Once upon a time there was an old woman who loved baking gingerbread. 
        She would bake gingerbread cookies, cakes, houses and gingerbread people, 
        all decorated with chocolate and peppermint, caramel candies and colored ingredients.
    """.trimIndent()

    val words1 = Poveste.split("\\s+".toRegex())  // Spargem textul pe cuvinte ignorând multiple spații
    println("Cuvintele din poveste [${words1.count()}]:")
    println(words1.joinToString(" "))

    val words2 = words1.map { it.trim('.', ',', '\n') }  // Eliminăm punctuația de la finalul fiecărui cuvânt

    println("\nPovestea tradusă ar suna cam așa:")
    for (item in words2) {
        print(Dictionar[item] ?: "[$item]") // Dacă nu există în dicționar, îl afișăm între paranteze
        print(" ")
    }
}
