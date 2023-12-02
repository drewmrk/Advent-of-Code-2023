fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        for (line in input) {
            val firstNumIndex = line.indexOfFirst { it.digitToIntOrNull() != null }
            val secondNumIndex = line.indexOfLast { it.digitToIntOrNull() != null }

            if (firstNumIndex != -1 && secondNumIndex != -1) {
                result += (line[firstNumIndex].toString() + line[secondNumIndex].toString()).toInt()
            } else if (firstNumIndex != -1 && secondNumIndex == -1) {
                result += (line[firstNumIndex].toString() + line[firstNumIndex].toString()).toInt()
            } else if (firstNumIndex == -1 && secondNumIndex != -1) {
                result += (line[secondNumIndex].toString() + line[secondNumIndex].toString()).toInt()
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        for (line in input) {
            val replacedLine = line
                .replace("zero", "zero0zero")
                .replace("one", "one1one")
                .replace("two", "two2two")
                .replace("three", "three3three")
                .replace("four", "four4four")
                .replace("five", "five5five")
                .replace("six", "six6six")
                .replace("seven", "seven7seven")
                .replace("eight", "eight8eight")
                .replace("nine", "nine9nine")

            val firstNumIndex = replacedLine.indexOfFirst { it.digitToIntOrNull() != null }
            val secondNumIndex = replacedLine.indexOfLast { it.digitToIntOrNull() != null }

            if (firstNumIndex != -1 && secondNumIndex != -1) {
                result += (replacedLine[firstNumIndex].toString() + replacedLine[secondNumIndex].toString()).toInt()
            } else if (firstNumIndex != -1 && secondNumIndex == -1) {
                result += (replacedLine[firstNumIndex].toString() + replacedLine[firstNumIndex].toString()).toInt()
            } else if (firstNumIndex == -1 && secondNumIndex != -1) {
                result += (replacedLine[secondNumIndex].toString() + replacedLine[secondNumIndex].toString()).toInt()
            }
        }

        return result
    }

    val part1Input = readInput("Day01")
    part1(part1Input).println()

    val part2Input = readInput("Day01")
    part2(part2Input).println()
}
