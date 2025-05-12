package ExercitiulLaborator1.factory

class FactoryProducer  {
    fun getFactory(choice: String): AbstractFactory{
        return if (choice == "EliteFactory")
            EliteFactory()
        else if (choice == "HappyWorkerFactory")
            HappyWorkerFactory()
        else
            throw IllegalArgumentException("Operatie ilegala")
    }



}