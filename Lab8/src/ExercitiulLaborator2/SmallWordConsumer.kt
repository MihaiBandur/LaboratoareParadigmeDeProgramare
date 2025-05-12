package ExercitiulLaborator2

class SmallWordConsumer(val caretaker: Caretaker): Observer
{
    var smallWordConsumer = 0;
    override fun update(str:String)
    {
        if(str.length<=7)
        {
            smallWordConsumer++
            print("Small word consumer: $str \ n")
            if(smallWordConsumer%10 == 0)
            {
                val memento = caretaker.getSavedStates()[caretaker.getSavedStates().size%10]
                print("Restorring state: ${memento.getState()}")
            }
        }
    }

}