package ExercitiulLaborator1.factory.chain

interface Handler {
    fun handleRequest(forwardDirection: String, messageToBeProcessed: String)
}