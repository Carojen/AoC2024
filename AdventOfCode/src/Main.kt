import java.io.File
import kotlin.math.abs
import kotlin.math.sign


fun main() {
  println("Which day do you want to run?")
    val dayAndPart = readln()
    val day = dayAndPart.split(" ")[0]
    val part = dayAndPart.split(" ").last()
    val input = ReadFile(day = day)
    var result = "NaN"

    if(day == "1")
        result = Day1().RunPart(part, input)
    else if(day == "2")
        result = Day2().RunPart(part, input)
    else if(day == "3")
        result = Day3().RunPart(part, input)

    println("The result of $dayAndPart is ${result}")
    println(result)
}

fun ReadFile(day: String): String
{
    println("Read file input: " + day)
    val input = File("input/"+day+".txt").inputStream().readBytes().toString(Charsets.UTF_8)
    return input
}
fun SplitLines(input: String): Array<String>
{
    val lines = input.split("\n").toTypedArray()
    return lines
}