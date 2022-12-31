package gr.alx.adventofcode.day9

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RopeMoverTest {
    @Test
    fun `should move head one step to the right`() {

        val ropePosition: RopePosition = RopeMover().move("R 1")

        assertThat(ropePosition.head.x).isEqualTo(1)
        assertThat(ropePosition.head.y).isEqualTo(0)
    }

    @Test
    fun `should move head one step to the left`() {

        val ropePosition: RopePosition = RopeMover().move("L 1")

        assertThat(ropePosition.head.x).isEqualTo(-1)
        assertThat(ropePosition.head.y).isEqualTo(0)
    }

    @Test
    fun `should move head one step down`() {

        val ropePosition: RopePosition = RopeMover().move("D 1")

        assertThat(ropePosition.head.x).isEqualTo(0)
        assertThat(ropePosition.head.y).isEqualTo(-1)
    }

    @Test
    fun `should move head one step up`() {

        val ropePosition: RopePosition = RopeMover().move("U 1")

        assertThat(ropePosition.head.x).isEqualTo(0)
        assertThat(ropePosition.head.y).isEqualTo(1)
    }

    @Test
    fun `should move head two steps to the right and tail one to the right`() {

        val ropePosition: RopePosition = RopeMover().move("R 2")

        assertThat(ropePosition.head.x).isEqualTo(2)
        assertThat(ropePosition.tail.x).isEqualTo(1)
    }

    @Test
    fun `should move head two steps to the right and record tail moves`() {

        val ropePosition: RopePosition = RopeMover().move("R 2")

        assertThat(ropePosition.head.x).isEqualTo(2)
        assertThat(ropePosition.visited).contains(Position(0, 0), Position(1, 0))
    }

    @Test
    fun `should move with test input`() {

        val input = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent()

        val ropePosition: RopePosition = RopeMover().move(input.lines())

        assertThat((ropePosition.visited.size)).isEqualTo(13)

    }

    @Test
    fun `should move with  input`() {

        val moves = ReadInputHelper.readLinesFromResource("/day9/rope.txt")


        val ropePosition: RopePosition = RopeMover().move(moves)

        assertThat((ropePosition.visited.size)).isEqualTo(6266)

    }


}
