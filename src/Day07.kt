fun main() {
    val pointMapping: Map<String, Long> = mapOf(
        "quintuple" to 20,
        "quadruple" to 19,
        "full" to 18,
        "triple" to 17,
        "double" to 16,
        "single" to 15,
        "high" to 14,
        "A" to 13,
        "K" to 12,
        "Q" to 11,
        "J" to 10,
        "T" to 9,
        "9" to 8,
        "8" to 7,
        "7" to 6,
        "6" to 5,
        "5" to 4,
        "4" to 3,
        "3" to 2,
        "2" to 1,
        "X" to 0
    )

    data class Hand(var cards: String, val bidAmt: Long, val handType: String, val originalCards: String)

    fun determineHandType(cards: String): String {
        val cardAmts: MutableMap<Char, Long> = mutableMapOf()

        for (card in cards) {
            if (cardAmts.containsKey(card)) {
                cardAmts[card] = cardAmts[card]!! + 1
            } else {
                cardAmts[card] = 1
            }
        }

        if (cardAmts.values.contains(5)) {
            return "quintuple"
        } else if (cardAmts.values.contains(4)) {
            if (cards.contains("J")) return "quintuple"

            return "quadruple"
        } else if (cardAmts.values.contains(3) && cardAmts.values.contains(2)) {
            if (cards.contains("J")) return "quintuple"

            return "full"
        } else if (cardAmts.values.contains(3)) {
            if (cards.contains("J")) return "quadruple"

            return "triple"
        } else if (cardAmts.values.count() { it.toInt() == 2 } == 2) {
            if (cards.contains('J')) {
               if (cardAmts['J']!!.toInt() == 2) return "quadruple"
               else return "full"
            }

            return "double"
        } else if (cardAmts.values.contains(2)) {
            if (cards.contains("J")) return "triple"

            return "single"
        } else {
            if (cards.contains("J")) return "single"

            return "high"
        }
    }

    fun inputToHands(input: List<String>): MutableList<Hand> {
        val hands: MutableList<Hand> = mutableListOf()

        for (line in input) {
            val cards = line.substringBefore(" ")
            val bidAmt = line.substringAfter(" ").toLong()
            val handType = determineHandType(cards)
            println(handType)

            hands.add(Hand(cards, bidAmt, handType, cards.replace('J', 'X')))
        }

        return hands
    }

    fun part1(input: List<String>): Long {
        var result: Long = 0

        val hands: MutableList<Hand> = inputToHands(input)

        hands.sortWith(compareBy<Hand>
            { pointMapping[it.handType] }.thenBy
            { pointMapping[it.cards[0].toString()] }.thenBy
            { pointMapping[it.cards[1].toString()] }.thenBy
            { pointMapping[it.cards[2].toString()] }.thenBy
            { pointMapping[it.cards[3].toString()] }.thenBy
            { pointMapping[it.cards[4].toString()] }
        )

        for (handIndex in hands.indices) {
            result += (handIndex + 1).toLong() * hands[handIndex].bidAmt
        }

        return result
    }

    fun part2(input: List<String>): Long {
        var result: Long = 0

        val hands: MutableList<Hand> = inputToHands(input)

        hands.sortWith(compareBy<Hand>
            { pointMapping[it.handType] }.thenBy
            { pointMapping[it.originalCards[0].toString()] }.thenBy
            { pointMapping[it.originalCards[1].toString()] }.thenBy
            { pointMapping[it.originalCards[2].toString()] }.thenBy
            { pointMapping[it.originalCards[3].toString()] }.thenBy
            { pointMapping[it.originalCards[4].toString()] }
        )

        for (handIndex in hands.indices) {
            result += (handIndex + 1).toLong() * hands[handIndex].bidAmt
        }

        return result
    }

    val input = readInput("Day07")
    // part1(input).println()
    part2(input).println()
}