package ExercitiulLaborator2

class Observable( private var observers: MutableList<Observer>) {
    public fun add(observer: Observer){
        observers.add(observer)
    }
    public fun remove(observer: Observer){
        observers.remove(observer)
    }

    public fun NotifyAll(){
        for (observer in observers)
            observer.update("")
    }

}