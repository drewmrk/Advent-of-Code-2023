fun main() {
    val maxRedCubes = 12
    val maxGreenCubes = 13
    val maxBlueCubes = 14

    fun part1(input: List<String>): Int {
        var result = 0

        for (line in input) {
            val splitLine = line.split(":")
            val gameId = splitLine[0].split(" ")[1].toInt()

            var addGameId = true

            val picks = splitLine[1].split(";")
            for (colorPicks in picks) {
                val splitColorPicks = colorPicks.split(",")

                val reds = splitColorPicks.filter { it.contains("red") }
                val greens = splitColorPicks.filter { it.contains("green") }
                val blues = splitColorPicks.filter { it.contains("blue") }

                var redCount = 0
                var greenCount = 0
                var blueCount = 0

                if (reds.isNotEmpty()) {
                    redCount = reds[0].split(" ")[1].toInt()
                }

                if (greens.isNotEmpty()) {
                    greenCount = greens[0].split(" ")[1].toInt()
                }

                if (blues.isNotEmpty()) {
                    blueCount = blues[0].split(" ")[1].toInt()
                }

                if (redCount > maxRedCubes
                    || greenCount > maxGreenCubes
                    || blueCount > maxBlueCubes) {

                    addGameId = false

                    break
                }
            }

            if (addGameId) {
                result += gameId
            }
        }

        return result
    }

    val input = readInput("Day02")
    part1(input).println()
}