package gr.alx.adventofcode.twentythree.day4

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScratchcardsTest {
    @Test
    fun `should parse one card`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            """.trimIndent()

        val cards = Scratchcards().parse(input.lines())

        assertThat(cards).hasSize(1)
        assertThat(cards[0].id).isEqualTo(1)
        assertThat(cards[0].winningNumbers).containsExactly(41, 48, 83, 86, 17)
        assertThat(cards[0].ownNumbers).containsExactly(83, 86, 6, 31, 17, 9, 48, 53)

    }

    @Test
    fun `should calculate one card`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            """.trimIndent()

        val sum = Scratchcards().calculate(input.lines())

        assertThat(sum).isEqualTo(8)

    }

    @Test
    fun `should calculate sample`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
            """.trimIndent()

        val sum = Scratchcards().calculate(input.lines())

        assertThat(sum).isEqualTo(13)

    }

    @Test
    fun `should calculate actual input part 1`() {
        val sum = Scratchcards().calculate(ReadInputHelper.readLinesFromResource("/twentythree/day4/input.txt"))

        assertThat(sum).isEqualTo(23941)
    }

    @Test
    fun `should calculate sample for part 2`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
            """.trimIndent()

        val sum = Scratchcards().calculate2(input.lines())

        assertThat(sum).isEqualTo(30)

    }

    @Test
    fun `should calculate actual input for part 2`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
            """.trimIndent()

        val sum = Scratchcards().calculate2(ReadInputHelper.readLinesFromResource("/twentythree/day4/input.txt"))


        assertThat(sum).isEqualTo(30)

    }
}
