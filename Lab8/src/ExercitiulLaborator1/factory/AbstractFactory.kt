package ExercitiulLaborator1.factory

import ExercitiulLaborator1.factory.chain.Handler

abstract class AbstractFactory {
    abstract fun getHandler(handler: String): Handler
}