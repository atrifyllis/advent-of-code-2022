package gr.alx.adventofcode.day2

const val WIN: Int = 6
const val DRAW: Int = 3
const val LOSS: Int = 0

data class Round(val opponent: OpponentMove, val own: OwnMove) {
    fun calculateScore(): Int = roundScore() + moveScore()

    private fun moveScore(): Int {
        return when (own) {
            OwnMove.ROCK -> 1
            OwnMove.PAPER -> 2
            OwnMove.SCISSORS -> 3
        }
    }

    private fun roundScore(): Int {
        return when (own) {
            OwnMove.ROCK -> when (opponent) {
                OpponentMove.ROCK -> DRAW
                OpponentMove.PAPER -> LOSS
                OpponentMove.SCISSORS -> WIN
            }

            OwnMove.PAPER -> when (opponent) {
                OpponentMove.ROCK -> WIN
                OpponentMove.PAPER -> DRAW
                OpponentMove.SCISSORS -> LOSS
            }

            OwnMove.SCISSORS -> when (opponent) {
                OpponentMove.ROCK -> LOSS
                OpponentMove.PAPER -> WIN
                OpponentMove.SCISSORS -> DRAW
            }
        }
    }
}
