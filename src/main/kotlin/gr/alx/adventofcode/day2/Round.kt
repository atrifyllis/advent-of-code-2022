package gr.alx.adventofcode.day2

const val WIN: Int = 6
const val DRAW: Int = 3
const val LOSS: Int = 0

data class Round(val opponent: OpponentMove, val own: OwnMove) {
    fun calculateScore(): Int = roundScore() + moveScore()

    fun calculateAltScore(): Int = roundAltScore() + moveALtScore()

    private fun moveScore(): Int {
        return moveScore(own)
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

    private fun moveScore(own: OwnMove): Int {
        return when (own) {
            OwnMove.ROCK -> 1
            OwnMove.PAPER -> 2
            OwnMove.SCISSORS -> 3
        }
    }

    private fun moveALtScore(): Int {
        return when (own) {
            OwnMove.ROCK -> when (opponent) { // X = LOSS
                OpponentMove.ROCK -> moveScore(OwnMove.SCISSORS)
                OpponentMove.PAPER -> moveScore(OwnMove.ROCK)
                OpponentMove.SCISSORS -> moveScore(OwnMove.PAPER)
            }

            OwnMove.PAPER -> when (opponent) { // Y = DRAW
                OpponentMove.ROCK -> moveScore(OwnMove.ROCK)
                OpponentMove.PAPER -> moveScore(OwnMove.PAPER)
                OpponentMove.SCISSORS -> moveScore(OwnMove.SCISSORS)
            }

            OwnMove.SCISSORS -> when (opponent) { // Z = WIN
                OpponentMove.ROCK -> moveScore(OwnMove.PAPER)
                OpponentMove.PAPER -> moveScore(OwnMove.SCISSORS)
                OpponentMove.SCISSORS -> moveScore(OwnMove.ROCK)
            }
        }
    }

    private fun roundAltScore(): Int {
        return when (own) {
            OwnMove.ROCK -> LOSS // X = LOSS
            OwnMove.PAPER -> DRAW // Y = DRAW
            OwnMove.SCISSORS -> WIN // Z = WIN
        }
    }
}
