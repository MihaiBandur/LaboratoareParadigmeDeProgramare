import kotlin.properties.Delegates

// Funcție extensie pentru verificarea unui număr prim
fun Int.isPrime(): Boolean {
    if (this < 2) return false
    for (i in 2..kotlin.math.sqrt(this.toDouble()).toInt()) {
        if (this % i == 0) return false
    }
    return true
}

// Variabilă cu vetoable - acceptă doar numere prime
var primeNumber: Int by Delegates.vetoable(2) { _, old, new ->
    if (new.isPrime()) {
        println("Valoare acceptată: $new")
        true
    } else {
        println("Valoare respinsă: $new nu este număr prim")
        false
    }
}

fun main() {
    primeNumber = 7    
    primeNumber = 10
    primeNumber = 13
    println("Valoarea finală: $primeNumber")
}
