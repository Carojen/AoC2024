import java.io.File
import kotlin.math.abs


fun main() {
    val name = "Kotlin"
  println("Which day do you want to run?")
    val dayAndPart = readln()
    val day = dayAndPart.split(" ")[0]
    val input = ReadFile(day = day)
    var result = "NaN"

    if(dayAndPart == "1 a")
    {
        result = Day1a(input)
    }
    else if(dayAndPart == "1 b")
    {
        result = Day1b(input)
    }
    println("The result of $dayAndPart is ${result}")

    println(result)
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
        val values = line.split("\\s+ ".toRegex()).toTypedArray()
        firstList.add(values[0].trim().toInt())
        secondList.add(values[1].trim().toInt())
    }
    firstList.sort()
    secondList.sort()
    var totalDistance:Int = 0
    for(i in firstList.indices)
    {
        var distance = abs(firstList[i]-secondList[i])
        totalDistance += distance
    }
    return totalDistance.toString()
}
fun Day1b(input: String):String
{
    var firstList: MutableList<Int> = mutableListOf()
    var secondList: MutableList<Int> = mutableListOf()
    val lines = input.split("\n").toTypedArray()
    for(line in lines)
    {
        val values = line.split("\\s+ ".toRegex()).toTypedArray()
        firstList.add(values[0].trim().toInt())
        secondList.add(values[1].trim().toInt())
    }
    firstList.sort()
    secondList.sort()
    val similarityMap:MutableMap<Int,Int> = mutableMapOf()
    var totalSimilarity = 0
    for(item in firstList)
    {
        if(similarityMap.containsKey(item))
        {
            totalSimilarity += similarityMap.getValue(item)
        }
        else
        {
            var numberOfItem = 0
            for(other in secondList)
            {
                if(other == item)
                    numberOfItem++
            }
            totalSimilarity += numberOfItem * item
        }
    }
    return totalSimilarity.toString()
}