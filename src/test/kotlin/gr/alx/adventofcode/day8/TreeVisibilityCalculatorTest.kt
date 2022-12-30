package gr.alx.adventofcode.day8

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TreeVisibilityCalculatorTest {

    @Test
    fun `should parse grid`() {
        val input = """
            111
            121
            111
        """.trimIndent()

        val grid = TreeVisibilityCalculator().parseGrid(input.lines())

        assertThat(grid[1][1]).isEqualTo(2)
    }

    @Test
    fun `should count visible`() {
        val input = """
            111
            121
            111
        """.trimIndent()

        val count = TreeVisibilityCalculator().countVisibleTrees(input.lines())

        assertThat(count).isEqualTo(9)
    }

    @Test
    fun `should count visible in test input`() {
        val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent()

        val count = TreeVisibilityCalculator().countVisibleTrees(input.lines())

        assertThat(count).isEqualTo(21)
    }

    @Test
    fun `should count visible in input`() {
        val trees = ReadInputHelper.readLinesFromResource("/day8/trees.txt")

        val count = TreeVisibilityCalculator().countVisibleTrees(trees)

        assertThat(count).isEqualTo(1816)
    }
}
