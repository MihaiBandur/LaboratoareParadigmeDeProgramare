package ExercitiulLaborator2

class LargeWordConsumer(var caretaker: Caretaker) : Observer {
    var largeWordConsumer = 0
    override fun update(str:String)
    {
        if(str.length>7)
        {
            ++largeWordConsumer
            print("Large word consumer: $str \n")
            if (largeWordConsumer % 7 == 0)
            {
                val memento = caretaker.getSavedStates()[caretaker.getSavedStates().size%10]
                print("Restorring state: ${memento.getState()} \n")
            }
        }
    }

}