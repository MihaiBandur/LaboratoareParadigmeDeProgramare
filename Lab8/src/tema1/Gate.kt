package tema1

interface Gate
{
    public val inputs: List<Boolean>
    public fun output(): Boolean
}