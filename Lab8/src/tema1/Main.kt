package tema1

fun fsm(gate:Gate):Boolean
{
    var rez:Boolean = true
    for(i in gate.inputs)
    {
        rez = rez and i
    }
    return rez
}
fun main() {
    var and2GateBuilder = And2GateBuiler()
    and2GateBuilder.addInput(true)
    and2GateBuilder.addInput(false)
    var and2Gate = and2GateBuilder.build()
    println(fsm(and2Gate))

    var and3GateBuilder = And3GateBuiler()
    and3GateBuilder.addInput(true)
    and3GateBuilder.addInput(true)
    and3GateBuilder.addInput(false)
    var and3Gate = and3GateBuilder.build()
    println(fsm(and3Gate))

    var and4GateBuilder = And4GateBuilder()
    and4GateBuilder.addInput(true)
    and4GateBuilder.addInput(false)
    and4GateBuilder.addInput(true)
    and4GateBuilder.addInput(false)
    var and4Gate = and4GateBuilder.build()
    println(fsm(and4Gate))

    var and8GateBuilder = And8GateBuilder()
    and8GateBuilder.addInput(true)
    and8GateBuilder.addInput(true)
    and8GateBuilder.addInput(true)
    and8GateBuilder.addInput(true)
    and8GateBuilder.addInput(true)
    and8GateBuilder.addInput(true)
    and8GateBuilder.addInput(true)
    and8GateBuilder.addInput(true)
    var and8Gate = and8GateBuilder.build()
    println(fsm(and8Gate))
}