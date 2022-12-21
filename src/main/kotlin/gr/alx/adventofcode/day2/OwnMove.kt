package gr.alx.adventofcode.day2

enum class OwnMove(val move: String) {

    ROCK("X"), PAPER("Y"), SCISSORS("Z");

    companion object {
        fun getEnumFromMove(move: String): OwnMove {
            return OwnMove.values().find { it.move == move } ?: throw IllegalArgumentException()
        }
    }
}
