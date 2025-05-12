package tema1
abstract class GateBuilder
{
    protected val inputs:MutableList<Boolean> = mutableListOf<Boolean>()

    fun addInput(boolean: Boolean):GateBuilder
    {
        inputs.add(boolean)
        return this
    }

    public abstract fun build(): Gate
}