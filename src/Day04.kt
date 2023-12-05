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

            result += gamePoints
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        var cards: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

        for (line in input.indices) {
            val splitLine = input[line].split("|")
            val winningNumbers = splitLine[0].split(":")[1].trim().split(" ")
            val yourNumbers = splitLine[1].trim().split(" ")

            var winningNumbersCount = 0

            for (number in winningNumbers) {
                if (number != "" && yourNumbers.contains(number)) {
                    winningNumbersCount++
                }
            }

            cards[line + 1] = Pair(1, winningNumbersCount)
        }

        for (card in cards.keys) {
            for (a in 0..<cards[card]!!.first) {
                for (i in card + 1..<cards[card]!!.second + card + 1) {
                    if (cards.containsKey(i)) {
                        cards[i] = Pair(cards[i]!!.first + 1, cards[i]!!.second)
                    }
                }
            }
        }

        return cards.values.sumOf { it.first }
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}