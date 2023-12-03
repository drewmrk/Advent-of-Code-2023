fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        val numberRegex = Regex("\\d+")

        var lineLength = 0

        for (line in input.indices) {
            if (lineLength == 0) { lineLength = input[line].length }

            val numbers = numberRegex.findAll(input[line])

            for (number in numbers) {
                val value = number.groups[0]!!.value
                val indexes = number.groups[0]!!.range

                for (index in indexes) {
                    if ((index - 1 >= 0 && input[line][index - 1] != '.' && !input[line][index - 1].isDigit()) // Check immediate left
                        || (index + 1 <= input[line].length - 1 && input[line][index + 1] != '.' && !input[line][index + 1].isDigit()) // Check immediate right
                        || (line - 1 >= 0 && index - 1 >= 0 && input[line - 1][index - 1] != '.' && !input[line - 1][index - 1].isDigit()) // Check diagonal upper left
                        || (line - 1 >= 0 && index + 1 <= input[line - 1].length - 1 && input[line - 1][index + 1] != '.' && !input[line - 1][index + 1].isDigit()) // Check diagonal upper right
                        || (line + 1 <= input.size - 1 && index - 1 >= 0 && input[line + 1][index - 1] != '.' && !input[line + 1][index - 1].isDigit()) // Check diagonal lower left
                        || (line + 1 <= input.size - 1 && index + 1 <= input[line + 1].length - 1 && input[line + 1][index + 1] != '.' && !input[line + 1][index + 1].isDigit()) // Check diagonal lower right
                        || (line + 1 <= input.size - 1 && index <= input[line + 1].length - 1 && input[line + 1][index] != '.' && !input[line + 1][index].isDigit()) // Check directly below
                        || (line - 1 >= 0 && index <= input[line - 1].length - 1 && input[line - 1][index] != '.' && !input[line - 1][index].isDigit()) // Check directly above
                        ) {

                        result += value.toInt()

                        break
                    }
                }
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        val numberRegex = Regex("\\d+")
        val gearRegex = Regex("\\*+")

        for (line in input.indices) {
            val gears = gearRegex.findAll(input[line])

            for (gear in gears) {
                val index = gear.groups[0]!!.range.first
                val numbers: MutableList<Int> = mutableListOf()

                val lineAboveNumbers = numberRegex.findAll(input[line - 1])
                val lineBelowNumbers = numberRegex.findAll(input[line + 1])
                val lineNumbers = numberRegex.findAll(input[line])

                // Check immediate left
                if ((index - 1 >= 0 && input[line][index - 1] != '.' && input[line][index - 1].isDigit())) {
                    numbers.add(lineNumbers.filter { it.groups[0]!!.range.contains(index - 1) }.map { it.groups[0]!!.value }.first().toInt())
                }

                // Check immediate right
                if (index + 1 <= input[line].length - 1 && input[line][index + 1] != '.' && input[line][index + 1].isDigit()) {
                    numbers.add(lineNumbers.filter { it.groups[0]!!.range.contains(index + 1) }.map { it.groups[0]!!.value }.first().toInt())
                }

                val directlyAbove = (line - 1 >= 0 && index <= input[line - 1].length - 1 && input[line - 1][index] != '.' && input[line - 1][index].isDigit())
                val diagonalUpperLeft = (line - 1 >= 0 && index - 1 >= 0 && input[line - 1][index - 1] != '.' && input[line - 1][index - 1].isDigit())
                val diagonalUpperRight = (line - 1 >= 0 && index + 1 <= input[line - 1].length - 1 && input[line - 1][index + 1] != '.' && input[line - 1][index + 1].isDigit())

                if (directlyAbove) {
                    numbers.add(lineAboveNumbers.filter { it.groups[0]!!.range.contains(index) }
                        .map { it.groups[0]!!.value }.first().toInt())
                } else  {
                    if (diagonalUpperLeft) {
                        numbers.add(lineAboveNumbers.filter { it.groups[0]!!.range.contains(index - 1) }
                            .map { it.groups[0]!!.value }.first().toInt())
                    }

                    if (diagonalUpperRight) {
                        numbers.add(lineAboveNumbers.filter { it.groups[0]!!.range.contains(index + 1) }
                            .map { it.groups[0]!!.value }.first().toInt())
                    }
                }

                val directlyBelow = (line + 1 <= input.size - 1 && index <= input[line + 1].length - 1 && input[line + 1][index] != '.' && input[line + 1][index].isDigit())
                val diagonalLowerLeft = (line + 1 <= input.size - 1 && index - 1 >= 0 && input[line + 1][index - 1] != '.' && input[line + 1][index - 1].isDigit())
                val diagonalLowerRight = (line + 1 <= input.size - 1 && index + 1 <= input[line + 1].length - 1 && input[line + 1][index + 1] != '.' && input[line + 1][index + 1].isDigit())

                if (directlyBelow) {
                    numbers.add(lineBelowNumbers.filter { it.groups[0]!!.range.contains(index) }
                        .map { it.groups[0]!!.value }.first().toInt())
                } else {
                    if (diagonalLowerLeft) {
                        numbers.add(lineBelowNumbers.filter { it.groups[0]!!.range.contains(index - 1) }
                            .map { it.groups[0]!!.value }.first().toInt())
                    }

                    if (diagonalLowerRight) {
                        numbers.add(lineBelowNumbers.filter { it.groups[0]!!.range.contains(index + 1) }
                            .map { it.groups[0]!!.value }.first().toInt())
                    }
                }

                if (numbers.size == 2) result += numbers[0] * numbers[1]
            }
        }

        return result
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}