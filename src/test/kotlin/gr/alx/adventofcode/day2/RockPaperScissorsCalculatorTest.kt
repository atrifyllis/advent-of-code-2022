package gr.alx.adventofcode.day2

import gr.alx.adventofcode.ReadInputHelper
import gr.alx.adventofcode.ReadInputHelper.Companion.splitStringToLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RockPaperScissorsCalculatorTest {

    @Test
    fun `should translate one round moves`() {

        val input = """
            A Y
        """.trimIndent()

        val rounds: List<Round> = RockPaperScissorsCalculator().readRounds(splitStringToLines(input))

        assertThat(rounds).hasSize(1)
        assertThat(rounds[0].own).isEqualTo(OwnMove.PAPER)
        assertThat(rounds[0].opponent).isEqualTo(OpponentMove.ROCK)
    }


    @Test
    fun `should calculate score for one round`() {

        val input = """
            A Y
        """.trimIndent()

        val score: Int = RockPaperScissorsCalculator().calculateScore(splitStringToLines(input))

        assertThat(score).isEqualTo(8)
    }


    @Test
    fun `should calculate score for input`() {

        val input = ReadInputHelper.readLinesFromResource("/day2/rock_paper_scissors_input.txt")

        val score: Int = RockPaperScissorsCalculator().calculateScore(input)

        assertThat(score).isEqualTo(14264)
    }

    @Test
    fun `should calculate alternate score for input`() {

        val input = ReadInputHelper.readLinesFromResource("/day2/rock_paper_scissors_input.txt")

        val score: Int = RockPaperScissorsCalculator().calculateAltScore(input)

        assertThat(score).isEqualTo(12382)
    }
}
