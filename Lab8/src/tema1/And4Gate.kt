package tema1

class And4Gate(override val inputs: List<Boolean>): Gate {
    override fun output(): Boolean {

        return inputs.all { it }
    }
}