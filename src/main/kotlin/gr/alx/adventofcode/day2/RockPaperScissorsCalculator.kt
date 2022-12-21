package gr.alx.adventofcode.day2

class RockPaperScissorsCalculator {
    fun calculateScore(input: List<String>): Int = readRounds(input).sumOf { it.calculateScore() }
    internal fun readRounds(input: List<String>): List<Round> {

        return input
            .map { it.split(" ") }
            .map {
                Round(OpponentMove.getEnumFromMove(it[0]), OwnMove.getEnumFromMove(it[1]))
            }
    }
}
