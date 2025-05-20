fun main()
{
    val originMap = mapOf(1 to "abc", 2 to "def", 3 to "ghi")

    println(originMap)

    val invertedMap = originMap.map { (key, value) -> value to key }. toMap();

    print(invertedMap)
}