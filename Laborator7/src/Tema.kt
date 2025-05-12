import java.io.File
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//calasa implementeaza interfata comparable
class HistoryLog(val timestamp: LocalDateTime, val command: String):Comparable<HistoryLog>
{
    override fun compareTo(other: HistoryLog): Int
    {
        return this.timestamp.compareTo(other.timestamp)
    }
}

fun HistoryRead(filepath: String, maxEntries:Int = 50): MutableMap<LocalDateTime, HistoryLog>?
{
    var map = mutableMapOf<LocalDateTime, HistoryLog>()
    val dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    var file: File? = null
    try
    {
        file = File(filepath)
        //creez o lista pentru memora liniile
        var list = mutableListOf<String>()
        //aici voi pastra o linie din sir
        var line = String()
        //realizez citirea linie cu linie din fisier
        var reader = file.bufferedReader()
        while(reader.readLine().also { line = it } != null)
        {
            list.add(line)
        }
        //obtin acum indexul care ma intereseaza
        val ln = list.size
        var index:Int
        if(ln <= maxEntries * 5)
        {
            index = 0;
        }
        else
        {
            index = list.size - maxEntries
        }
        //fac parcurgerea liniilor
        var startDate:LocalDateTime? = null
        var command:String? = null
        for(i in index..<list.size)
        {
            //am extras linia curenta
            line = list[i]
            when
            {
                //ma voi folosi de trim pt a sterge spatiile de la inceput si final
                //verific daca linia incepe cu "Start-Date:"
                line.startsWith("Start-Date:")->{
                    startDate = LocalDateTime.parse(line.substring(11).trim(), dateformatter)
                }
                //verific daca incepe cu Commandline:
                line.startsWith("Commandline:")-> {
                    command = line.substring(12).trim()
                }
                line.startsWith("End-Date:")->{
                    //in acest caz eu am parcurs informatiile pt un history si trebuie sa il adaug la map
                    if(startDate != null && command != null)
                    {
                        val h = HistoryLog(startDate, command)
                        map[startDate] = h
                        //acum fac startDate si command null pt a marca ca acestea au fost introduse si a nu pune duplicate
                        startDate = null
                        command = null
                    }
                }
            }
        }
        return map
    }
    catch (e:Exception)
    {
        println(e.stackTrace)
        return null
    }
}

//functia de maxim
fun<T:Comparable<T>> max(obj1: T, obj2:T): T
{
    if(obj1 > obj2)
    {
        return obj1
    }
    else
    {
        return obj2
    }
}

fun<T: Comparable<T>> replace(target:T, replace:T, container:MutableMap<T,T>)
{
    if(container.containsKey(target))
    {
        container[target] = replace
    }
}

fun main()
{
    val historyLogs = HistoryRead("/var/log/apt/history.log")

    val r1 = HistoryLog(LocalDateTime.of(2025,4,6,11,24), command = "test1")
    val r3 = HistoryLog(LocalDateTime.of(2025,4,6,11,24), command = "test2")
    val r2 = HistoryLog(LocalDateTime.of(2025,4,6,11,24), command = "test1")
    println("${max(r1, r2)}")
    var map = mutableMapOf<HistoryLog, HistoryLog>()
    map[r1] = r2
    println("$map")
    replace(r1, r3, map)
    println("$map")
}