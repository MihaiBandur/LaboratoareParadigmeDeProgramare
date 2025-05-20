import kotlin.math.sqrt

fun dist(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Double {
    return sqrt((p1.first - p2.first).toDouble().pow(2) + (p1.second - p2.second).toDouble().pow(2))
}

fun main() {
    print("Introduceți numărul de puncte: ")
    val n = readLine()?.toIntOrNull() ?: return println("Valoare invalidă.")

    println("Introduceți cele $n puncte (x y):")
    val puncte = List(n) {
        val (x, y) = readLine()?.split(" ")?.map { it.toInt() } ?: listOf(0, 0)
        x to y
    }

    // distanțe între punctele consecutive
    val distanteConsecutive = puncte.zipWithNext().map { dist(it.first, it.second) }

    // distanța de la ultimul la primul pentru a închide poligonul
    val distInchidere = dist(puncte.last(), puncte.first())

    val perimetru = distanteConsecutive.sum() + distInchidere

    println("Perimetrul poligonului este: %.2f".format(perimetru))
}

fun Double.pow(exp: Int) = Math.pow(this, exp.toDouble())