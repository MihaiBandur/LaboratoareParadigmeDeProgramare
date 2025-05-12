package tema1

class And2GateBuiler: GateBuilder() {
    override fun build(): Gate {
        return GateBridge(And2Gate(listOf(inputs[0], inputs[1])))
    }
}