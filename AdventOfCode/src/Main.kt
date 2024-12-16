import java.io.File


fun main() {
    while(true)
    {
        println("Which day do you want to run?")
        val dayAndPart = readln()

        if(dayAndPart.contains(("q")))
        {
            println("Exiting program ...")
            return
        }

        val day = dayAndPart.split(" ")[0]
        val part = dayAndPart.split(" ").last()
        val input = readFile(day = day)
        var result = "NaN"

        when (day) {
            "1" -> result = Day1().runPart(part, input)
            "2" -> result = Day2().runPart(part, input)
            "3" -> result = Day3().runPart(part, input)
            "4" -> result = Day4().runPart(part, input)
        }

        println("The result of $dayAndPart is $result")
        println("\n $result \n ----------")
    }
}

fun readFile(day: String): String
{
    println("Read file input: $day")
    val input = File("input/$day.txt").inputStream().readBytes().toString(Charsets.UTF_8)
    return input
}