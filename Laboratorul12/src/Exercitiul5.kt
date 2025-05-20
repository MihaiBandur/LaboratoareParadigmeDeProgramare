fun main()
{
    print("Introduceti un numar n:")
    val n = readLine()?.toIntOrNull() ?: return println("valoare invalida")

    val lista = listOf(1, 2, 3)

    val listaReplicata = lista.flatMap { element -> List(n) {element} }
    print(listaReplicata)
}