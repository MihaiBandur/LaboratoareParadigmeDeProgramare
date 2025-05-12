import java.time.LocalDateTime
import java.io.File
import java.lang.Character.isDigit
import java.time.DateTimeException
import java.time.OffsetDateTime
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*

//voi folosi o data class pentru SyslogRecord
data class SyslogRecord(val timestamp:LocalDateTime, val hostName:String, val appName:String, val pid: Int?, val logSys:String)

//aceasta funcite va realiza analiza unei linii din fisier si va returna un obiect care contine informatiile necesare

fun parseSyslog(l: String): SyslogRecord? {
    val pattern = """^(\S+) (.+) (.+)\[(\d+)?\]: (.+)$""".toRegex()
    val match = pattern.matchEntire(l)
    if (match != null) {
        val group = match.groupValues
        val time = try {
            OffsetDateTime.parse(group[1]).toLocalDateTime()
        } catch (e: DateTimeException) {
            return null
        }

        val hostName = group[2]
        val appName = group[3]
        val pid = group[4].toIntOrNull()
        val logSys = group[5]

        return SyslogRecord(time, hostName, appName, pid, logSys)
    } else {
        // println("Linie ignorată: $l")
        return null
    }
}

//aceasta este functia care va citi informatiile din fisierul cu syslog
fun SyslogRead(filePath: String, min:Long):List<SyslogRecord>
{
    val now = LocalDateTime.now()
    val l = mutableListOf<SyslogRecord>()
    try
    {
        //deschid  fisierul
        val f = File(filePath)

        //citesc liniile din fisier
        val lines = f.readLines()
        for(line in lines)
        {
            val syslog = parseSyslog(line)
            if (syslog != null && now.minusMinutes(min).isBefore(syslog.timestamp))
            {
                l.add(syslog)
            }
        }
    }
    catch (e:Exception)
    {
        e.printStackTrace();
    }
    return l
}

//creez o functie care returneaza valori pid diferite de null
fun GroupPIDNotNull(list:List<SyslogRecord>): Map<String, List<SyslogRecord>>
{
    //filtrez liniile care au PID-ul nenul
    val l = mutableListOf<SyslogRecord>()
    for(line in list)
    {
        if(line.pid != null)
        {
            l.add(line)
        }
    }

    //in acest map voi returna liniile din syslog cu PID-ul nenul folosind appName ca si key
    val m = mutableMapOf<String, MutableList<SyslogRecord>>()
    for(it in l)
    {
        if(!m.containsKey(it.appName))
        {
            //inseamna ca am dat prima data peste aplicatia asta deci voi aduga cheia la map
            m[it.appName] = mutableListOf<SyslogRecord>()
        }
        m[it.appName]?.add(it)
    }
    return m
}

//functie care realizeaza sotarea informatiei dupa log si apoi le afiseaza
fun sortAndPrintSyslog(m:Map<String, List<SyslogRecord>>)
{
    for((appName, rec) in m)
    {
        println("Application: $appName")
        rec.sortedBy { it.logSys }
        for(it in rec)
        {
            println("${it.timestamp} ${it.hostName} [${it.pid}] ${it.logSys}")
        }
    }
}

fun main()
{
    //path-ul pt fiser
    val path = "/home/bandur-mihai/IdeaProjects/Laborator7/syslog"
    //val path = "/var/log/syslog"
    val min = 30L

    //realizaz citirea infromatiilor din fisier
    val l = SyslogRead(path, min)

    //realizez gruparea informatiilor
    val m = GroupPIDNotNull(l)

    //fac sortarea si afisarea
    sortAndPrintSyslog(m)
}