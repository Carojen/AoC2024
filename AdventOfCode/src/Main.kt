import java.io.File
import kotlin.math.abs


fun main() {
    val name = "Kotlin"
  println("Which day do you want to run?")
    val day = readln()
    val input = ReadFile(day = day)

    if(day == "1a")
    {
        val result = Day1a(input)
        println("The result of $day is ${result}")
        println(result)
    }


    for (i in 1..5) {
         //println("i = $i")
    }
}

fun ReadFile(day: String):String
{
    println("Read file input: " + day)
    val input = File("input/"+day+".txt").inputStream().readBytes().toString(Charsets.UTF_8)
    return input
}


fun Day1a(input: String):String
{
    var firstList: MutableList<Int> = mutableListOf()
    var secondList: MutableList<Int> = mutableListOf()
    val lines = input.split("\n").toTypedArray()
    for(line in lines)
    {
        //println(line)
        val values = line.split("\\s+ ".toRegex()).toTypedArray()
        //println("first "+values[0].trim()+" " +values[0].length)
        //println("second "+values[1].trim()+" "+values[1].length)

        firstList.add(values[0].trim().toInt())
        secondList.add(values[1].trim().toInt())
    }
    firstList.sort()
    secondList.sort()
    //println(firstList)
    //println(secondList)
    var totalDistance:Int = 0
    for(i in firstList.indices)
    {
        //println(firstList[i].toString() + " - " + secondList[i].toString() + " = " + (abs(firstList[i] - secondList[i])))
        var distance = abs(firstList[i]-secondList[i])
        totalDistance += distance
    }
    return totalDistance.toString()
}