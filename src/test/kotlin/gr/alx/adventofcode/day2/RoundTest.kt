package gr.alx.adventofcode.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RoundTest {
    @Test
    fun `should calculate score when opponent=ROCK and own=PAPER`() {
        val score = Round(
            opponent = OpponentMove.ROCK,
            own = OwnMove.PAPER
        ).calculateScore()

        assertThat(score).isEqualTo(8)
    }
}
