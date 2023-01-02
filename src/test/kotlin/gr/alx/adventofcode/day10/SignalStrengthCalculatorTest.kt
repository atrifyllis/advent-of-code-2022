package gr.alx.adventofcode.day10

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SignalStrengthCalculatorTest {

    @Test
    fun `should parse noop command`() {
        val input = """
            noop
        """.trimIndent()
        val signal: Signal = SignalStrengthCalculator().parse(input.lines())

        assertThat(signal.x).isEqualTo(1)
        assertThat(signal.cycle).isEqualTo(1)
    }

    @Test
    fun `should parse addx commands`() {
        val input = """
            noop
            addx 3
            addx -5
        """.trimIndent()
        val signal: Signal = SignalStrengthCalculator().parse(input.lines())

        assertThat(signal.x).isEqualTo(-1)
        assertThat(signal.cycle).isEqualTo(5)
        assertThat(signal.map[1]).isEqualTo(1)
        assertThat(signal.map[2]).isEqualTo(1)
        assertThat(signal.map[3]).isEqualTo(1)
        assertThat(signal.map[4]).isEqualTo(4)
        assertThat(signal.map[5]).isEqualTo(4)
    }

    @Test
    fun `should parse large test input`() {

        val input = """
            addx 15
            addx -11
            addx 6
            addx -3
            addx 5
            addx -1
            addx -8
            addx 13
            addx 4
            noop
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx -35
            addx 1
            addx 24
            addx -19
            addx 1
            addx 16
            addx -11
            noop
            noop
            addx 21
            addx -15
            noop
            noop
            addx -3
            addx 9
            addx 1
            addx -3
            addx 8
            addx 1
            addx 5
            noop
            noop
            noop
            noop
            noop
            addx -36
            noop
            addx 1
            addx 7
            noop
            noop
            noop
            addx 2
            addx 6
            noop
            noop
            noop
            noop
            noop
            addx 1
            noop
            noop
            addx 7
            addx 1
            noop
            addx -13
            addx 13
            addx 7
            noop
            addx 1
            addx -33
            noop
            noop
            noop
            addx 2
            noop
            noop
            noop
            addx 8
            noop
            addx -1
            addx 2
            addx 1
            noop
            addx 17
            addx -9
            addx 1
            addx 1
            addx -3
            addx 11
            noop
            noop
            addx 1
            noop
            addx 1
            noop
            noop
            addx -13
            addx -19
            addx 1
            addx 3
            addx 26
            addx -30
            addx 12
            addx -1
            addx 3
            addx 1
            noop
            noop
            noop
            addx -9
            addx 18
            addx 1
            addx 2
            noop
            noop
            addx 9
            noop
            noop
            noop
            addx -1
            addx 2
            addx -37
            addx 1
            addx 3
            noop
            addx 15
            addx -21
            addx 22
            addx -6
            addx 1
            noop
            addx 2
            addx 1
            noop
            addx -10
            noop
            noop
            addx 20
            addx 1
            addx 2
            addx 2
            addx -6
            addx -11
            noop
            noop
            noop
        """.trimIndent()
        val strength: Long = SignalStrengthCalculator().calculateStrength(input.lines())

        assertThat(strength).isEqualTo(13140)
    }

    @Test
    fun `should parse part 1 input`() {

        val input = ReadInputHelper.readLinesFromResource("/day10/signal.txt")

        val strength: Long = SignalStrengthCalculator().calculateStrength(input)

        assertThat(strength).isEqualTo(13860)
    }
}
