import javax.swing.event.MenuListener

//Numar variabil de argumente
fun<T> varargFun(vararg items:T){
    items.forEach (::println)
}
//Parametru vararg pentru functii lambda
fun <T> emit(t: T, vararg  listeners: (T) -> Unit) = listeners.forEach {
    listener -> listener(t)
}
fun main()
{
    varargFun(11)
    varargFun(1.1, 2,2)
    varargFun("Cristina", "Andrei", "Roxana")

    emit(1, ::println, {i -> println(i *2 )})
    emit(listOf('a', 'b', 'c'),
        ::println,
        {
            list-> println(list.joinToString (prefix = "<",
                                                postfix = ">",
                                                separator ="" )) }
    )
}