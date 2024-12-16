class Day3:Day() {
    override fun runPartOne(input: String): String
    {
        return findAndCalculateMultipliers(input, useEnablers = false).toString()
    }

    override fun runPartTwo(input: String): String {
        return findAndCalculateMultipliers(input, useEnablers = true).toString()
    }

    private fun findAndCalculateMultipliers(input: String, useEnablers: Boolean = false): Int
    {
        var totalAmount = 0
        val instructions = input.split("mul")

        var previousSection: String? = null
        var enabled = true
        for(section in instructions)
        {
            val numbers = section.split(")")[0].removePrefix("(").split(",")
            println("Check $numbers")
            if(previousSection != null && useEnablers)
            {
                println("Previous section: $previousSection")
                val dos = previousSection.split("do()")
                if(dos.size > 1)
                {
                    enabled = true
                    println("Found do()")
                }
                if(dos.last().contains("don't()"))
                {
                    enabled = false
                    println("Found later don't()")
                }
            }
            if(!enabled)
            {
                previousSection = section
                continue
            }
            if(numbers.count() < 2)
            {
                previousSection = section
                continue
            }

            if(numbers[0].toIntOrNull() != null && numbers[1].toIntOrNull() != null )
            {
                val product = numbers[0].toInt() * numbers[1].toInt()
                totalAmount += product
            }
            previousSection = section
        }
        return totalAmount
    }
}