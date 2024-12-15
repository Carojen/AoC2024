import kotlin.math.abs

class Day1: Day() {
    override fun RunPartOne(input: String): String
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
    override fun RunPartTwo(input: String): String
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

}