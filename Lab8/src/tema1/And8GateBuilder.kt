package tema1

class And8GateBuilder: GateBuilder() {
    override fun build(): Gate {

        return GateBridge(And8Gate(listOf(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5], inputs[6], inputs[7])))
    }
}