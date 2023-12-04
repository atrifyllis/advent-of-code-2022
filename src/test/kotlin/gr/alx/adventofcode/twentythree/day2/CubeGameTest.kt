package gr.alx.adventofcode.twentythree.day2

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CubeGameTest {

    @Test
    fun `should calculate sample input`() {
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """.trimIndent()

        val sumOfIds = CubeGame().calculate(input.lines())

        Assertions.assertThat(sumOfIds).isEqualTo(8)
    }

    @Test
    fun `should calculate actual input part 1`() {
        val sum =
            CubeGame().calculate(ReadInputHelper.readLinesFromResource("/twentythree/day2/input.txt"))

        Assertions.assertThat(sum).isEqualTo(2476)
    }

    @Test
    fun `should calculate sample input part 2`() {
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """.trimIndent()

        val sumOfIds = CubeGame().calculate2(input.lines())

        Assertions.assertThat(sumOfIds).isEqualTo(2286)
    }

    @Test
    fun `should calculate actual input part 2`() {
        val sum =
            CubeGame().calculate2(ReadInputHelper.readLinesFromResource("/twentythree/day2/input.txt"))

        Assertions.assertThat(sum).isEqualTo(54911)
    }
}
