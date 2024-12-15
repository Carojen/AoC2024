import kotlin.math.abs
import kotlin.math.sign

class Day2:Day() {
    override fun RunPartTwo(input: String): String
    {
        return RunDay(input, true)
    }

    override fun RunPartOne(input: String): String
    {
        return RunDay(input, false)
    }

    fun CheckLineSafety(line: String): Boolean
    {
        var currentLine = line.replace(Regex("\\s+"), " ")
        val levels = currentLine.split(" ")
        var previousLevel: Int? = null
        var safe: Boolean = true
        for(i in levels.indices)
        {
            val currentLevel = levels[i].trim().toIntOrNull()
            if(currentLevel == null)
                continue

            if(previousLevel == null)
            {
                previousLevel = currentLevel
                continue
            }

            if(currentLevel == previousLevel)
            {
                safe = false
            }
            else if(abs(previousLevel - currentLevel) < 1 || abs(previousLevel - currentLevel) > 3)
            {
                safe = false
            }
            else if(i > 1 && (currentLevel-previousLevel).sign != (previousLevel - levels[i-2].toInt()).sign)
            {
                safe = false
            }
            previousLevel = currentLevel
            if(!safe)
                break
        }
        return safe
    }

    fun RunDay(input: String, allowOneOff: Boolean = false):String
    {
        val lines = SplitLines(input)
        var numberOfSafeReports = 0
        for(line in lines)
        {
            if(line.trim().length == 0) continue

            val currentLine = line.trim()
            var safe = CheckLineSafety(currentLine)
            if(!safe && allowOneOff)
            {
                val levelArray: Array<Int> = currentLine.split("\\s+".toRegex()).map{it.toInt()}.toTypedArray()
                for(i in levelArray.indices)
                {
                    var shortLine: String = ""
                    for(levelIndex in levelArray.indices)
                    {
                        if(i == levelIndex) continue
                        shortLine += levelArray[levelIndex].toString() + " "
                    }
                    if(shortLine.length == 0) continue
                    safe = CheckLineSafety(shortLine)
                    println("   Check $shortLine: $safe (${shortLine.length})")
                    if(safe)
                    {
                        break
                    }
                }
            }
            if(safe)
                numberOfSafeReports++
            println("Line $currentLine: $safe")
        }

        return numberOfSafeReports.toString()
    }

}