package ExercitiulLaborator1.factory

import ExercitiulLaborator1.factory.chain.Handler
import ExercitiulLaborator1.factory.chain.HappyWorkerHandler

class HappyWorkerFactory: AbstractFactory() {
    override fun getHandler(handler: String): Handler {
        if(handler == "HappyWorker")
            return HappyWorkerHandler()
        else
            throw IllegalArgumentException("Operatie ilegata")
    }

}