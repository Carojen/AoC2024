abstract class Day
{
    fun runPart(part:String, input: String): String
    {
        return if(part == "b" || part == "2")
            runPartTwo(input)
        else
            runPartOne(input)
    }
    abstract fun runPartOne(input: String): String
    abstract fun runPartTwo(input: String): String
}