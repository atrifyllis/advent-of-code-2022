package gr.alx.adventofcode.day2

enum class OpponentMove(val move: String) {

    ROCK("A"), PAPER("B"), SCISSORS("C");

    companion object {
        fun getEnumFromMove(move: String): OpponentMove {
            return values().find { it.move == move } ?: throw IllegalArgumentException()
        }
    }
}
