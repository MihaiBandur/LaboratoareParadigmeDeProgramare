package tema1

class And3Gate(override val inputs: List<Boolean>): Gate {
    override fun output(): Boolean {
        return inputs.all { it }
    }
}