package tema1

class And4GateBuilder: GateBuilder() {
    override fun build(): Gate {

        return GateBridge(And4Gate(listOf(inputs[0], inputs[1], inputs[2], inputs[3])))
    }
}