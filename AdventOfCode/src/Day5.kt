class Day5: Day() {
    override fun runPartOne(input: String): String {
        var totalMiddlePages = 0
        val lines = input.lines()

        val rulesAndUpdates = lines.partition { it.contains("|")}
        val rules: MutableList<Pair<Int, Int>?> = MutableList(size = rulesAndUpdates.first.size) { null }

        for(ruleIndex in rulesAndUpdates.first.indices)
        {
            rules[ruleIndex] = processRule(lines[ruleIndex])
        }
        for(update in rulesAndUpdates.second)
        {
            if(update.isEmpty()) continue
            val validMiddlePageOrNegative = verifyUpdate(update, rulesAndUpdates.first)
            if(validMiddlePageOrNegative != -1)
                totalMiddlePages += validMiddlePageOrNegative
        }

        return totalMiddlePages.toString()
    }

    override fun runPartTwo(input: String): String {
        TODO("Not yet implemented")
    }

    private fun processRule(line:String): Pair<Int, Int>
    {
        return Pair(line[0].digitToInt(), line[1].digitToInt())
    }
    private fun verifyUpdate(update: String, rules: List<String>): Int
    {
        val pages:List<Int> = update.split(',').map{it.trim().toInt()}
        for(first in pages.indices)
        {
            var second = first + 1
            while(second <= pages.lastIndex)
            {
                val checkFor:String = "${pages[second]}|${pages[first]}"
                //println("Check $update -> $checkFor")
                if (rules.contains(checkFor))
                {
                    println("$update was not valid: $checkFor")
                    return -1
                }
                //println("Not found")
                second++
            }
        }
        println("$update was valid. ${pages[pages.size/2]}")
        return pages[pages.size/2]
    }
}