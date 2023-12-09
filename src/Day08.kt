fun main() {
    fun inputToNetwork(input: List<String>): Map<String, Pair<String, String>> {
        val result: MutableMap<String, Pair<String, String>> = mutableMapOf()

        for (lineIndex in 2..input.size - 1) {
            val key = input[lineIndex].substringBefore(" = ")
            val values = input[lineIndex].substringAfter(" = ").split(", ")

            val valuePair = Pair(values[0].substring(1, 4), values[1].substring(0, 3))

            result[key] = valuePair
        }

        return result
    }

    fun part1(input: List<String>): Int {
        val directions = input[0]

        val nodes = inputToNetwork(input)
        var currentKey = "AAA"

        var directionIndex = 0
        var totalMoves = 0
        while (currentKey != "ZZZ") {
            if (directionIndex == directions.length) {
                directionIndex = 0
            }

            if (directions[directionIndex] == 'L') {
                currentKey = nodes[currentKey]!!.first
            } else {
                currentKey = nodes[currentKey]!!.second
            }

            directionIndex++
            totalMoves++
        }

        return totalMoves
    }

    fun part2(input: List<String>): Int {
        val directions = input[0]

        val nodes = inputToNetwork(input)
        var currentKey: MutableList<String> = nodes.keys.filter { it[it.length - 1] == 'A' }.toMutableList()
        val currentKeySize = currentKey.size

        var directionIndex = 0
        var totalMoves = 0
        while (currentKey.filter { it[it.length - 1] == 'Z' }.size != currentKeySize) {
            totalMoves.println()
            currentKey.println()

            if (directionIndex == directions.length) {
                directionIndex = 0
            }

            if (directions[directionIndex] == 'L') {
                for (i in currentKey.indices) {
                    currentKey[i] = nodes[currentKey[i]]!!.first
                }
            } else {
                for (i in currentKey.indices) {
                    currentKey[i] = nodes[currentKey[i]]!!.second
                }
            }

            directionIndex++
            totalMoves++
        }

        return totalMoves
    }

    val input = readInput("Day08")
    // part1(input).println()
    part2(input).println()
}