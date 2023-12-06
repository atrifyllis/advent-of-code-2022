package gr.alx.adventofcode.twentythree.day3

import gr.alx.adventofcode.ReadInputHelper
import gr.alx.adventofcode.twentythree.day2.CubeGame
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GearRatiosTest {

    @Test
    fun `should calculate for one line`() {
        val input = """
            617*......
            """.trimIndent()

        val sumOfParts = GearRatios().calculate(input.lines())

        Assertions.assertThat(sumOfParts).isEqualTo(617)
    }

    @Test
    fun `should calculate for line above`() {
        val input = """
            467..114..
            ...*......
            """.trimIndent()

        val sumOfParts = GearRatios().calculate(input.lines())

        Assertions.assertThat(sumOfParts).isEqualTo(467)
    }

    @Test
    fun `should calculate for line below`() {
        val input = """
           ..35..633.
            ......#...
            """.trimIndent()

        val sumOfParts = GearRatios().calculate(input.lines())

        Assertions.assertThat(sumOfParts).isEqualTo(633)
    }

    @Test
    fun `should calculate when symbol is before`() {
        val input = """
           ..35#633.
            """.trimIndent()

        val sumOfParts = GearRatios().calculate(input.lines())

        Assertions.assertThat(sumOfParts).isEqualTo(35+633)
    }
    @Test
    fun `should calculate sample input`() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...\$.*....
            .664.598..
            """.trimIndent()

        val sumOfParts = GearRatios().calculate(input.lines())

        Assertions.assertThat(sumOfParts).isEqualTo(4361)
    }

    @Test
    fun `should calculate reddit sample input`() {
        val input = """
            12.......*..
            +.........34
            .......-12..
            ..78........
            ..*....60...
            78..........
            .......23...
            ....90*12...
            ............
            2.2......12.
            .*.........*
            1.1.......56
            """.trimIndent()

        val sumOfParts = GearRatios().calculate(input.lines())

        Assertions.assertThat(sumOfParts).isEqualTo(413)
    }

    @Test
    fun `should calculate actual input part 1`() {
        val sum =
                GearRatios().calculate(ReadInputHelper.readLinesFromResource("/twentythree/day3/input.txt"))

        Assertions.assertThat(sum).isEqualTo(2476)
    }

    @Test
    fun `should calculate actual input part 2`() {
        val sum =
                GearRatios().calculate2(ReadInputHelper.readLinesFromResource("/twentythree/day3/input.txt"))

        Assertions.assertThat(sum).isEqualTo(2476)
    }
}
