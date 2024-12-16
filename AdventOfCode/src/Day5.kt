class Day5: Day() {
    override fun runPartOne(input: String): String {
        var totalMiddlePages = 0
        val lines = input.lines()
        val rulesAndUpdates = lines.partition { it.contains("|")}

        for(update in rulesAndUpdates.second)
        {
            if(update.isEmpty()) continue
            val pages = update.split(',').map{it.trim().toInt()}
            val failedRulesAndMiddlePage = verifyUpdate(pages, rulesAndUpdates.first, failFast = true)
            if(failedRulesAndMiddlePage.second > 0)
                totalMiddlePages += failedRulesAndMiddlePage.second
        }

        return totalMiddlePages.toString()
    }

    override fun runPartTwo(input: String): String
    {
        var totalMiddlePages = 0
        val lines = input.lines()
        val rulesAndUpdates = lines.partition { it.contains("|")}

        for(update in rulesAndUpdates.second)
        {
            if(update.isEmpty()) continue
            var pages = update.split(',').map{it.trim().toInt()}.toMutableList()
            val failedRulesAndMiddlePage = verifyUpdate(pages, rulesAndUpdates.first, failFast = false)
            if(failedRulesAndMiddlePage.first.isNotEmpty())
            {
                println("Has failed rules")
                pages = sortUpdate(pages, failedRulesAndMiddlePage.first)
                totalMiddlePages += pages[pages.size/2]
            }
        }
        return totalMiddlePages.toString()
    }

    private fun sortUpdate(pages: MutableList<Int>, rules: List<String>): MutableList<Int>
    {
        val sortedPages = pages.sortedWith{ a, b -> comparePages(a, b, rules)}.toMutableList()
        println("$pages -> $sortedPages")
        return sortedPages
    }

    private fun comparePages(first: Int, second: Int, rules:List<String>): Int
    {
        if(rules.contains("$first|$second"))
        {
            //println("$first -> $second")
            return -1
        }
        else if(rules.contains("$second|$first"))
        {
            //println("$first <- $second")
            return 1
        }
        //println("$first - $second")
        return 0
    }

    private fun verifyUpdate(pages: List<Int>, rules: List<String>, failFast:Boolean): Pair<List<String>, Int>
    {
        val failedRules: MutableList<String> = mutableListOf()
        for(first in pages.indices)
        {
            var second = first + 1
            while(second <= pages.lastIndex)
            {
                val checkFor = "${pages[second]}|${pages[first]}"
                //println("Check $update -> $checkFor")
                if (rules.contains(checkFor))
                {
                    failedRules += checkFor
                    println("${pages.joinToString(",")} was not valid: $checkFor")

                    if(failFast)
                        return Pair(emptyList(), -1)
                }
                //println("Not found")
                second++
            }
        }
        if(failedRules.isEmpty())
            println("${pages.joinToString(",")} was valid. ${pages[pages.size/2]}")
        return Pair<List<String>,Int>(failedRules,pages[pages.size/2])
    }
}