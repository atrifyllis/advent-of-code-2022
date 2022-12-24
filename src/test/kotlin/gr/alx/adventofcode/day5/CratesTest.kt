package gr.alx.adventofcode.day5

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class CratesTest {
    @Test
    fun `should read one stack`() {

        val input = """
            [N] 
            [Z] 
             1  
        """.trimIndent()
        val stack: MutableMap<Int, Deque<Char>> = CratesArranger().readStacks(input.lines())

        assertThat(stack[1]?.pop()).isEqualTo('N')
        assertThat(stack[1]?.pop()).isEqualTo('Z')
    }

    @Test
    fun `should read multiple stacks`() {

        val input = """
                [D]    
            [N] [C]    
            [Z] [M] [P]
             1   2   3 
        """.trimIndent()
        val stack: MutableMap<Int, Deque<Char>> = CratesArranger().readStacks(input.lines())

        assertThat(stack[1]?.pop()).isEqualTo('N')
        assertThat(stack[1]?.pop()).isEqualTo('Z')

        assertThat(stack[2]?.pop()).isEqualTo('D')
        assertThat(stack[2]?.pop()).isEqualTo('C')
        assertThat(stack[2]?.pop()).isEqualTo('M')

        assertThat(stack[3]?.pop()).isEqualTo('P')
    }

    @Test
    fun `should read one procedure line`() {
        val input = """
            move 1 from 2 to 1
        """.trimIndent()

        val procedureList: List<Procedure> = CratesArranger().readProcedureLines(input.lines())

        assertThat(procedureList[0].size).isEqualTo(1)
        assertThat(procedureList[0].from).isEqualTo(2)
        assertThat(procedureList[0].to).isEqualTo(1)
    }

    @Test
    fun `should read stack lines and one procedure line and execute`() {
        val input = """
                [D]    
            [N] [C]    
            [Z] [M] [P]
             1   2   3 
            
            move 1 from 2 to 1
        """.trimIndent()

        val headStackElements: String = CratesArranger().execute(input.lines())

        assertThat(headStackElements).isEqualTo("DCP")

    }

    @Test
    fun `should read input and execute procedure`() {
        val crates = ReadInputHelper.readLinesFromResource("/day5/crates.txt")

        val headStackElements: String = CratesArranger().execute(crates)

        assertThat(headStackElements).isEqualTo("WSFTMRHPP")

    }

    @Test
    fun `should read stack lines and one procedure line and execute part 2`() {
        val input = """
                [D]    
            [N] [C]    
            [Z] [M] [P]
             1   2   3 
            
            move 1 from 2 to 1
        """.trimIndent()

        val headStackElements: String = CratesArranger().executePart2(input.lines())

        assertThat(headStackElements).isEqualTo("DCP")

    }

    @Test
    fun `should read input and execute part 2 procedure`() {
        val crates = ReadInputHelper.readLinesFromResource("/day5/crates.txt")

        val headStackElements: String = CratesArranger().executePart2(crates)

        assertThat(headStackElements).isEqualTo("GSLCMFBRP")

    }
}
