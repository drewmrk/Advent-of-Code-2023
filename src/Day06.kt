fun main() {
    data class Race(val time: Long, val recordDistance: Long)

    fun inputToRaces(input: List<String>): List<Race> {
        val races: MutableList<Race> = mutableListOf()
        val times = input[0].split(":")[1].trim().split(" ").filter { it.isNotBlank() }
        val distances = input[1].split(":")[1].trim().split(" ").filter { it.isNotBlank() }

        for (i in times.indices) {
            races.add(Race(times[i].toLong(), distances[i].toLong()))
        }

        return races
    }

    fun inputToRacesCombine(input: List<String>): Race {
        val time = input[0].split(":")[1].trim().split(" ").filter { it.isNotBlank() }.joinToString("").toLong()
        val distance = input[1].split(":")[1].trim().split(" ").filter { it.isNotBlank() }.joinToString("").toLong()

        return Race(time, distance)
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

    fun part2(input: List<String>): Int {
        val race = inputToRacesCombine(input)

        var waysToWin = 0
        for (i in 0..race.time) {
            val distanceToTravel = (race.time - i) * i

            if (distanceToTravel > race.recordDistance) {
                waysToWin++
            }
        }

        return waysToWin
    }

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}