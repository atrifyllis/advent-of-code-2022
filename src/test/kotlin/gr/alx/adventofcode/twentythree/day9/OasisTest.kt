package gr.alx.adventofcode.twentythree.day9

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OasisTest {
    @Test
    fun `should parse a value history line`() {
        val input = """
            0 3 6 9 12 15
            """.trimIndent()

        val part1 = Oasis().part1(input.lines())

        assertThat(part1).isEqualTo(18)


    }

    @Test
    fun `should parse sample`() {

        var input = ReadInputHelper.readLinesFromResource("/twentythree/day9/sample.txt")

        val part1 = Oasis().part1(input)

        assertThat(part1).isEqualTo(114)
    }

    @Test
    fun `should parse part 1`() {

        var input = ReadInputHelper.readLinesFromResource("/twentythree/day9/input.txt")

        val part1 = Oasis().part1(input)

        assertThat(part1).isEqualTo(1757008019)
    }

    @Test
    fun `should parse a value history line for part 2`() {
        val input = """
            10 13 16 21 30 45
            """.trimIndent()

        val part1 = Oasis().part2(input.lines())

        assertThat(part1).isEqualTo(18)


    }

    @Test
    fun `should parse part 2`() {

        var input = ReadInputHelper.readLinesFromResource("/twentythree/day9/input.txt")

        val part1 = Oasis().part2(input)

        assertThat(part1).isEqualTo(1757008019)
    }


}
