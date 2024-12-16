import kotlin.math.abs

class Day1: Day() {
    private fun createLists(input: String ): Pair<MutableList<Int>, MutableList<Int>>
    {
        val firstList: MutableList<Int> = mutableListOf()
        val secondList: MutableList<Int> = mutableListOf()
        val lines = input.lines()
        for(line in lines)
        {
            val values = line.split("\\s+ ".toRegex()).toTypedArray()
            firstList.add(values[0].trim().toInt())
            secondList.add(values[1].trim().toInt())
        }
        firstList.sort()
        secondList.sort()

        return Pair(firstList, secondList)
    }

    override fun runPartOne(input: String): String
    {
        val lists = createLists(input)
        var totalDistance = 0
        for(i in lists.first.indices)
        {
            val distance = abs(lists.first[i]-lists.second[i])
            totalDistance += distance
        }
        return totalDistance.toString()
    }
    override fun runPartTwo(input: String): String
    {
        val lists = createLists(input)
        val similarityMap:MutableMap<Int,Int> = mutableMapOf()
        var totalSimilarity = 0
        for(item in lists.first)
        {
            if(similarityMap.containsKey(item))
            {
                totalSimilarity += similarityMap.getValue(item)
            }
            else
            {
                var numberOfItem = 0
                for(other in lists.second)
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