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



        return result
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}