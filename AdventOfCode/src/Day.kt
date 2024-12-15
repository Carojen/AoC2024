abstract class Day
{
    fun RunPart(part:String, input: String): String
    {
        if(part == "b" || part == "2")
            return RunPartTwo(input)
        else
            return RunPartOne(input)
    }
    abstract fun RunPartOne(input: String): String
    abstract fun RunPartTwo(input: String): String
}