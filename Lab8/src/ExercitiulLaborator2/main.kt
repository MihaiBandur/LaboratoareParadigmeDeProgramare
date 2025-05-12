package ExercitiulLaborator2

import java.io.File

fun main() {
    val caretaker = Caretaker()
    val originator = Originator(" ")

    val smallObserver = SmallWordConsumer(caretaker)
    val largeObserver = LargeWordConsumer(caretaker)


    val fileText = File("/Users/bandu/Desktop/ebook.txt").readText()
    val words = fileText
        .replace("[^\\w\\s]".toRegex(), "") // eliminăm semnele de punctuație
        .split("\\s+".toRegex())

    for(word in words){
        originator.setMessage(word)
        val memento = originator.savedToMemento()
        originator.restoreFromMemento(memento)

        caretaker.addMemento(memento)
        smallObserver.update(word)
        largeObserver.update(word)
    }



    println("\nProcesare finalizată.")
}
