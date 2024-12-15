class Day4:Day() {

    val xmas: String = "XMAS"
    val samx: String = "SAMX"
    val wordLength = 4

    override fun RunPartOne(input: String): String
    {
        var totalAmount = 0
        val lines: List<String> = splitLines(input.trim()).map{it.trim()}
        totalAmount += findHorizontal(lines)
        totalAmount += findVertical(lines)
        totalAmount += findDiagonal(lines)
        return totalAmount.toString()
    }

    override fun RunPartTwo(input: String): String {
        TODO("Not yet implemented")
    }

    private fun findHorizontal(lines:List<String>): Int
    {
        var totalHorizontalAmount = 0
        for (currentLine in lines)
        {
            totalHorizontalAmount += currentLine.windowed(wordLength){if(it == xmas) 1 else 0}.sum()
            totalHorizontalAmount += currentLine.windowed(wordLength){if(it == samx) 1 else 0}.sum()
        }
        println("Horizontal: $totalHorizontalAmount")
        return totalHorizontalAmount
    }

    private fun findVertical(lines:List<String>):Int
    {
        var totalVerticalAmount = 0
        for(i in lines[0].indices)
        {
            var column:String = ""
            for(line in lines)
            {
                column += line[i]
            }
            totalVerticalAmount += column.windowed(wordLength){if(it == xmas) 1 else 0}.sum()
            totalVerticalAmount += column.windowed(wordLength){if(it == samx) 1 else 0}.sum()
        }
        println("Vertical: $totalVerticalAmount")
        return totalVerticalAmount
    }

    private fun findDiagonal(lines:List<String>):Int
    {
        var totalDiagonalAmount = 0
        var checkUp: Boolean = true
        var checkDown: Boolean = true
        var checkLeft: Boolean = true
        var checkRight: Boolean = true

        for (lineIndex in lines.indices)
        {
            checkUp = (lineIndex >= wordLength - 1)
            checkDown = (lineIndex < lines.count() - wordLength)

            val currentLine = lines[lineIndex]
            for (columnIndex in currentLine.indices)
            {
                if(currentLine[columnIndex] != 'X')
                    continue
                //println("Found X at $lineIndex,$columnIndex")

                checkLeft = (columnIndex >= wordLength - 1)
                checkRight = (columnIndex < currentLine.count() - wordLength)

                if(checkUp && checkLeft)
                {
                    //println("Check UpLeft")
                    totalDiagonalAmount += checkDiagonalDirection(lines, lineIndex = lineIndex, columnIndex = columnIndex,
                        vertical = -1, horizontal = -1)
                }
                if(checkUp && checkRight)
                {
                    //println("Check UpRight")
                    totalDiagonalAmount += checkDiagonalDirection(lines, lineIndex = lineIndex, columnIndex = columnIndex,
                        vertical = -1, horizontal = 1)
                }
                if(checkDown && checkLeft)
                {
                    //println("Check DownLeft")
                    totalDiagonalAmount += checkDiagonalDirection(lines, lineIndex = lineIndex, columnIndex = columnIndex,
                        vertical = 1, horizontal = -1)
                }
                if(checkDown && checkRight)
                {
                    //println("Check DownRight")
                    totalDiagonalAmount += checkDiagonalDirection(lines, lineIndex = lineIndex, columnIndex = columnIndex,
                        vertical = 1, horizontal = 1)
                }
            }
        }
        println("Diagonal: $totalDiagonalAmount")
        return totalDiagonalAmount
    }

    private fun checkDiagonalDirection(lines:List<String>, lineIndex: Int, columnIndex:Int,
                                       vertical: Int, horizontal: Int):Int
    {
        //println("Check indices $lineIndex,$columnIndex " +
        //        "to ${lineIndex+(vertical*(wordLength-1))},${columnIndex+(horizontal*(wordLength-1))}")

        for(i in 1..<wordLength)
        {
            val letter:Char = lines[lineIndex+(vertical*i)][columnIndex+(horizontal*i)]
            if(letter != xmas[i])
            {
                return 0
            }
        }
        return 1
    }
}