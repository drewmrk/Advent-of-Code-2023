fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        for (line in input) {
            val splitLine = line.split("|")
            val winningNumbers = splitLine[0].split(":")[1].trim().split(" ")
            val yourNumbers = splitLine[1].trim().split(" ")

            var gamePoints = 0

            for (number in winningNumbers) {
                if (number != "" && yourNumbers.contains(number)) {
                    if (gamePoints == 0) {
                        gamePoints++
                    } else {
                        gamePoints *= 2
                    }
                }
            }

            println(gamePoints)

            result += gamePoints
        }

        return result
    }

    val input = readInput("Day04")
    part1(input).println()
}