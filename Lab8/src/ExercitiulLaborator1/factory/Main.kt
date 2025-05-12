package ExercitiulLaborator1.factory

import ExercitiulLaborator1.factory.chain.CEOHandler
import ExercitiulLaborator1.factory.chain.ExecutiveHandler
import ExercitiulLaborator1.factory.chain.HappyWorkerHandler
import ExercitiulLaborator1.factory.chain.ManagerHandler

fun main(args: Array<String>)
{

    // se creeaza 1xFactoryProducer, 1xEliteFactory, 1xHappyWorkerFactory
    //...
    val factoryProducer = FactoryProducer()
    val eliteFactory = factoryProducer.getFactory("EliteFactory")
    val happyWorkerFactory = factoryProducer.getFactory("HappyWorkerFactory")


    // crearea instantelor (prin intermediul celor 2 fabrici):
    // 2xCEOHandler, 2xExecutiveHandler, 2xManagerHandler, 2xHappyWorkerHandler
    //...
    val ceoHandler1 = eliteFactory.getHandler("CEOHandler") as CEOHandler
    val ceoHandler2 = eliteFactory.getHandler("CEOHandler") as CEOHandler
    val executiveHandler1 = eliteFactory.getHandler("ExecutiveHandler") as ExecutiveHandler
    val executiveHandler2 = eliteFactory.getHandler("ExecutiveHandler") as ExecutiveHandler
    val managerHandler1 = eliteFactory.getHandler("ManagerHandler") as ManagerHandler
    val managerHandler2 = eliteFactory.getHandler("ManagerHandler") as ManagerHandler
    val happyWorkerHandler1 = happyWorkerFactory.getHandler("HappyWorker") as HappyWorkerHandler
    val happyWorkerHandler2 = happyWorkerFactory.getHandler("HappyWorker") as HappyWorkerHandler

    // se construieste lantul (se verifica intai diagrama de obiecte si se realizeaza legaturile)
    //...
    //Contruiesc lantul
    ceoHandler1.next1 = executiveHandler1
    executiveHandler1.next1 = managerHandler1
    managerHandler1.next1 = happyWorkerHandler1

    ceoHandler2.next1 = executiveHandler1
    executiveHandler2.next1 = managerHandler2
    managerHandler2.next1 = happyWorkerHandler2

    //realizez legaturile
    executiveHandler1.next2 = ceoHandler1
    managerHandler1.next2 = executiveHandler1
    happyWorkerHandler1.next2 = managerHandler1

    executiveHandler2.next2 = ceoHandler2
    managerHandler2.next2 = executiveHandler2
    happyWorkerHandler2.next2 = managerHandler2

    // se executa lantul utilizand atat mesaje de prioritate diferita, cat si directii diferite in lant
    //...
    ceoHandler1.handleRequest("forward", "Request 1: High priority")
    ceoHandler2.handleRequest("forward", "Request 2: Low priority")

    happyWorkerHandler1.handleRequest("backward", "Request 3: High priority")
    happyWorkerHandler2.handleRequest("backward", "Request 4: Low priority")
}