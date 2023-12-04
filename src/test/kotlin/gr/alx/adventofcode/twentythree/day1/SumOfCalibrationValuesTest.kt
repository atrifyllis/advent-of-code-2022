package gr.alx.adventofcode.twentythree.day1

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SumOfCalibrationValuesTest {

    @Test
    fun `should calculate empty input`() {
        val sum = SumOfCalibrationValues().calculate(listOf())

        assertThat(sum).isEqualTo(0)
    }

    @Test
    fun `should calculate sample input`() {
        var input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
            """.trimIndent()

        val sum = SumOfCalibrationValues().calculate(input.lines())

        assertThat(sum).isEqualTo(142)
    }

    @Test
    fun `should calculate actual input part 1`() {
        val sum =
            SumOfCalibrationValues().calculate(ReadInputHelper.readLinesFromResource("/twentythree/day1/input.txt"))

        assertThat(sum).isEqualTo(53334)
    }

    @Test
    fun `should calculate sample input 2`() {
        var input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
            """.trimIndent()

        val sum = SumOfCalibrationValues().calculatePart2(input.lines())

        assertThat(sum).isEqualTo(281)
    }

    @Test
    fun `should calculate actual input part 2`() {
        val sum =
            SumOfCalibrationValues().calculatePart2(ReadInputHelper.readLinesFromResource("/twentythree/day1/input.txt"))

        assertThat(sum).isEqualTo(52834)
    }


    @Test
    fun `should calculate when only one digit`() {
        var input = """
            znfqfjcspf8md
            """.trimIndent()

        val sum = SumOfCalibrationValues().calculatePart2(input.lines())

        assertThat(sum).isEqualTo(88)
    }

    @Test
    fun `should calculate for corner case (fo is start of four)`() {
        var input = """
            lfoneight4
            """.trimIndent()

        val sum = SumOfCalibrationValues().calculatePart2(input.lines())

        assertThat(sum).isEqualTo(14)
    }
}
