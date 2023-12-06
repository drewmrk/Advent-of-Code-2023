fun main() {
    data class Race(val time: Int, val recordDistance: Int)

    fun inputToRaces(input: List<String>): List<Race> {
        val races: MutableList<Race> = mutableListOf()
        val times = input[0].split(":")[1].trim().split(" ").filter { it.isNotBlank() }
        val distances = input[1].split(":")[1].trim().split(" ").filter { it.isNotBlank() }

        for (i in times.indices) {
            races.add(Race(times[i].toInt(), distances[i].toInt()))
        }

        return races
    }

    fun part1(input: List<String>): Int {
        var result = 0

        val races = inputToRaces(input)
        val numWaysToWinRaces: MutableMap<Int, Int> = mutableMapOf()

        var raceId = 0
        for (race in races) {
            var waysToWin = 0
            for (i in 0..race.time) {
                val distanceToTravel = (race.time - i) * i

                if (distanceToTravel > race.recordDistance) {
                    waysToWin++
                }
            }
            numWaysToWinRaces[raceId] = waysToWin
            raceId++
        }

        for (wins in numWaysToWinRaces.values) {
            if (result == 0) {
                result = wins
            } else {
                result *= wins
            }
        }

        return result
    }

    val input = readInput("Day06")
    part1(input).println()
}