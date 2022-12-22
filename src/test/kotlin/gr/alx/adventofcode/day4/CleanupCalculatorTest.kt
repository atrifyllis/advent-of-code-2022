package gr.alx.adventofcode.day4

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CleanupCalculatorTest {

    @Test
    fun `should expand range of sections`() {

        val input = "2-4"

        val sections: List<Int> = CleanupCalculator().expandRangeOfSections(input)

        assertThat(sections).containsExactly(2, 3, 4)
    }

    @Test
    fun `should expand pair of ranges to pair of sections`() {
        val input = "2-4,6-8"

        val sections: List<List<Int>> = CleanupCalculator().expandToSections(input)

        assertThat(sections[0]).containsExactly(2, 3, 4)
        assertThat(sections[1]).containsExactly(6, 7, 8)
    }

    @Test
    fun `should not find overlapping section ranges`() {

        val input = "2-4,6-8"

        val isOverlapping: Boolean = CleanupCalculator().hasOverlappingSectionRanges(input)

        assertThat(isOverlapping).isFalse
    }


    @Test
    fun `should find overlapping section ranges`() {

        val input = "2-4,2-8"

        val isOverlapping: Boolean = CleanupCalculator().hasOverlappingSectionRanges(input)

        assertThat(isOverlapping).isTrue
    }

    @Test
    fun `should sum up number of overlapping section ranges`() {

        val input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent()

        val sum: Int = CleanupCalculator().sumUpOverlappingSectionRanges(input.lines())

        assertThat(sum).isEqualTo(2)
    }

    @Test
    fun `should sum up overlapping section ranges for input`() {
        val sectionRanges = ReadInputHelper.readLinesFromResource("/day4/cleanup.txt")

        val sum: Int = CleanupCalculator().sumUpOverlappingSectionRanges(sectionRanges)

        assertThat(sum).isEqualTo(644)

    }
}
