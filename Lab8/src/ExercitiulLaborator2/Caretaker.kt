package ExercitiulLaborator2

class Caretaker {
    private  var savedStates: MutableList<Memento> =mutableListOf<Memento>();

    public fun addMemento(memento: Memento)
    {
        savedStates.add(memento)
    }

    public fun getSavedStates(): MutableList<Memento>
    {
        return savedStates
    }
}