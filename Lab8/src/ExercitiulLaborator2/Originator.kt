package ExercitiulLaborator2

class Originator(private  var message: String) {
    public fun savedToMemento(): Memento{
            return Memento(message)
    }
    public fun restoreFromMemento(memento: Memento){
        message = memento.getState()
    }
    public fun setMessage(message: String){
        this.message=message
    }
}