package ExercitiulLaborator1.factory.chain

class CEOHandler(var next1: Handler? = null, var next2: Handler? = null): Handler {
    override fun handleRequest(forwardDirection: String, messageToBeProcessed: String) {
        println("CEOHandler: $messageToBeProcessed")
        if(forwardDirection == "forward" )
        {
            next1?.handleRequest(forwardDirection, messageToBeProcessed)
        }
        else
        {
            next2?.handleRequest(forwardDirection, messageToBeProcessed)
        }
    }

}