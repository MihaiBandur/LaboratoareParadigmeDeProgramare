package org.example

import kotlin.math.sqrt

fun Int.prime():Boolean {
    if(this == 2)
    {
        return true
    }
    if(this == 0 || this == 1)
    {
        return false
    }
    if(this%2 == 0)
    {
        return false
    }
    val x:Int = sqrt(this.toDouble()).toInt()
    for(d in 3 ..  x step 2)
    {
        if(x%d == 0)
        {
            return false
        }
    }
    return true
}

fun main()
{
    println(3.prime())
    println(10.prime())
}