fun main() {
    data class History(val history: List<Long>, val differences: MutableList<Long>)

    fun inputToHistories(input: List<String>): List<History> {
        val result: MutableList<History> = mutableListOf()

        for (line in input) {
            val history = line.split(" ").map { it.toLong() }
            result.add(History(history, mutableListOf()))
        }

        return result
    }

    fun computeDifferenceSequence(differences: List<Long>, last: Long): Long {
        if (differences.sum() == 0.toLong()) {
            return last
        }

        val computedDifferences: List<Long> = (1..differences.size - 1).map { i -> differences[i] - differences[i - 1] }
        return computeDifferenceSequence(computedDifferences, last + computedDifferences.last())
    }

    fun part1(input: List<String>): Long {
        val histories: List<History> = inputToHistories(input)

        var result: Long = 0

        for (history in histories) {
            val amtToAdd = computeDifferenceSequence(history.history, history.history[history.history.size - 1])

            result += amtToAdd
        }


        return result
    }

    fun part2(input: List<String>): Long {
        val histories: List<History> = inputToHistories(input)

        var result: Long = 0

        for (history in histories) {
            val reversedHistory = history.history.reversed()
            val amtToAdd = computeDifferenceSequence(reversedHistory, reversedHistory.last())

            result += amtToAdd
        }


        return result
    }

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}