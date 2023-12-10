package gr.alx.adventofcode.twentythree.day8

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class WastelandTest {


    @Test
    fun `should calculate sample`() {
        val sum = Wasteland().part1(ReadInputHelper.readLinesFromResource("/twentythree/day8/sample.txt"))

        Assertions.assertThat(sum).isEqualTo(6)
    }

    @Test
    fun `should calculate actual input part 1`() {
        val sum = Wasteland().part1(ReadInputHelper.readLinesFromResource("/twentythree/day8/input.txt"))

        Assertions.assertThat(sum).isEqualTo(11309)
    }

}
