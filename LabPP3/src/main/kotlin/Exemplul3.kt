package org.example

class Birth(val year: Int, val month: Int, val day: Int) {
    override fun toString(): String {
        return "($day.$month.$year)"
    }
}

class Contact(val name: String, val phone: String, val birthDate: Birth) {
    fun printContact() {
        println("Name: $name, Mobile: $phone, Date: $birthDate")
    }
}

fun main() {
    val agenda = mutableListOf(
        Contact("Mihai", "0744321987", Birth(1900, 11, 25)),
        Contact("George", "0761332100", Birth(2002, 3, 14)),
        Contact("Liviu", "0231450211", Birth(1999, 7, 30)),
        Contact("Popescu", "0211342787", Birth(1955, 5, 12))
    )

    println("Agenda inițială:")
    for (persoana in agenda) {
        persoana.printContact()
    }

    println("\nAgenda după eliminarea contactului [George]:")
    agenda.removeIf { it.name == "George" }
    for (persoana in agenda) {
        persoana.printContact()
    }

    println("\nAgenda după eliminarea contactului [Liviu]:")
    agenda.removeIf { it.name == "Liviu" }
    for (persoana in agenda) {
        persoana.printContact()
    }
}
