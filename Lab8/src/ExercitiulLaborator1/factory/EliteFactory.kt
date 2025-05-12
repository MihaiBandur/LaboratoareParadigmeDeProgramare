package ExercitiulLaborator1.factory

import ExercitiulLaborator1.factory.chain.CEOHandler
import ExercitiulLaborator1.factory.chain.ExecutiveHandler
import ExercitiulLaborator1.factory.chain.Handler
import ExercitiulLaborator1.factory.chain.ManagerHandler

class EliteFactory: AbstractFactory() {
    override fun getHandler(handler: String): Handler {
        if(handler == "ManagerHandler")
            return ManagerHandler()
        else if (handler == "ExecutiveHandler")
            return ExecutiveHandler()
        else if (handler == "CEOHandler")
            return CEOHandler()
        else
        {
            throw IllegalArgumentException("Operatie ilegala")
        }
    }
}