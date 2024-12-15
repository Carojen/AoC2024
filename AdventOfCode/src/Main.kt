import java.io.File
import kotlin.math.abs
import kotlin.math.sign


fun main() {
  println("Which day do you want to run?")
    val dayAndPart = readln()
    val day = dayAndPart.split(" ")[0]
    val input = ReadFile(day = day)
    var result = "NaN"

    if(dayAndPart == "1 a")
        result = Day1a(input)
    else if(dayAndPart == "1 b")
        result = Day1b(input)
    else if(dayAndPart == "2 a")
        result = Day2a(input)
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

fun Day2a(input: String):String
{
    val lines = SplitLines(input)
    var numberOfSafeReports = 0
    for(line in lines)
    {
        //println("Line $line")
        val levels = line.split(" ")
        var previousLevel: Int = 0
        var safe: Boolean = true
        for(i in levels.indices)
        {
            if(i == 0)
            {
                previousLevel = levels[i].toInt()
                continue
            }
            //println(levels[i])
            if(levels[i].trim().toIntOrNull() == null)
                continue

            val currentLevel = levels[i].trim().toInt()
            //println(previousLevel.toString() + " " + currentLevel.toString())
            if(currentLevel == previousLevel)
            {
                safe = false
                //println("Same")
                break
            }
            else if(abs(previousLevel - currentLevel) < 1 || abs(previousLevel - currentLevel) > 3)
            {
                safe = false
                //println("Too big difference")
            }
            else if(i > 1 && (currentLevel-previousLevel).sign != (previousLevel - levels[i-2].toInt()).sign)
            {
                safe = false
                //println("Different signs")
            }
            previousLevel = currentLevel
            if(!safe)
                break
        }
        if(safe)
            numberOfSafeReports++
        //println(safe)
    }

    return numberOfSafeReports.toString()
}

fun Day1a(input: String):String
{
    val firstList: MutableList<Int> = mutableListOf()
    val secondList: MutableList<Int> = mutableListOf()
    val lines = SplitLines(input)
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