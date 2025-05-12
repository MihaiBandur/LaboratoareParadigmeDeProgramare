package tema1

class And3GateBuiler: GateBuilder() {
    override fun build(): Gate {
        return GateBridge(And3Gate(listOf( inputs[0], inputs[1], inputs[2])))
    }

}