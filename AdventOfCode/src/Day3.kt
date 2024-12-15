class Day3:Day() {
    override fun RunPartOne(input: String): String
    {
        return FindAndCalculateMultipliers(input).toString()
    }

    override fun RunPartTwo(input: String): String {
        TODO("Not yet implemented")
    }

    private fun FindAndCalculateMultipliers(input: String): Int
    {
        var totalAmount:Int = 0
        val instructions = input.split("mul")
        for(section in instructions)
        {
            val numbers = section.split(")")[0].removePrefix("(").split(",")
            if(numbers.count() < 2) continue

            if(numbers[0].toIntOrNull() != null && numbers[1].toIntOrNull() != null )
            {
                val product = numbers[0].toInt() * numbers[1].toInt()
                totalAmount += product
            }
        }
        return totalAmount
    }
}