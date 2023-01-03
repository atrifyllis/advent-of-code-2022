package gr.alx.adventofcode.day11

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class MonkeyBusinessCalculatorTest {
    @Test
    fun `should parse single monkey instruction`() {
        val input = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
        """.trimIndent()

        val instructions: List<Instruction> = MonkeyBusinessCalculator().parse(input.lines())

        assertThat(instructions[0]).usingRecursiveComparison().isEqualTo(
            Instruction(
                monkey = BigInteger.ZERO,
                itemLevels = mutableListOf(BigInteger("79"), BigInteger("98")),
                worryOperation = Op.MULTI,
                worryOperand = BigInteger("19"),
                divisibleBy = BigInteger("23"),
                throwToWhenTrue = BigInteger("2"),
                throwToWhenFalse = BigInteger("3")
            )
        )
    }

    @Test
    fun `should parse multiple monkey instructions`() {
        val input = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
                
            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0
        """.trimIndent()

        val instructions: List<Instruction> = MonkeyBusinessCalculator().parse(input.lines())

        assertThat(instructions[0]).usingRecursiveComparison().isEqualTo(
            Instruction(
                monkey = BigInteger("0"),
                itemLevels = mutableListOf(BigInteger("79"), BigInteger("98")),
                worryOperation = Op.MULTI,
                worryOperand = BigInteger("19"),
                divisibleBy = BigInteger("23"),
                throwToWhenTrue = BigInteger("2"),
                throwToWhenFalse = BigInteger("3")
            )
        )

        assertThat(instructions[1]).usingRecursiveComparison().isEqualTo(
            Instruction(
                monkey = BigInteger("1"),
                itemLevels = mutableListOf(54, 65, 75, 74).map { it.toBigInteger() }.toMutableList(),
                worryOperation = Op.ADD,
                worryOperand = BigInteger("6"),
                divisibleBy = BigInteger("19"),
                throwToWhenTrue = BigInteger("2"),
                throwToWhenFalse = BigInteger("0")
            )
        )
    }

    @Test
    fun `should execute simple round`() {
        val input = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
                
            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0
                
            Monkey 2:
              Starting items: 79, 60, 97
              Operation: new = old * old
              Test: divisible by 13
                If true: throw to monkey 1
                If false: throw to monkey 3
                
            Monkey 3:
              Starting items: 74
              Operation: new = old + 3
              Test: divisible by 17
                If true: throw to monkey 0
                If false: throw to monkey 1
        """.trimIndent()

        val itemsPerMonkey: Map<BigInteger, List<BigInteger>> = MonkeyBusinessCalculator().calculate(input.lines(), 1)

        assertThat(itemsPerMonkey[BigInteger.ZERO]).isEqualTo(listOf(20, 23, 27, 26).map { it.toBigInteger() })
        assertThat(itemsPerMonkey[BigInteger.ONE]).isEqualTo(listOf(2080, 25, 167, 207, 401, 1046).map { it.toBigInteger() })


    }


    @Test
    fun `should execute with simple input`() {
        val input = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
                
            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0
                
            Monkey 2:
              Starting items: 79, 60, 97
              Operation: new = old * old
              Test: divisible by 13
                If true: throw to monkey 1
                If false: throw to monkey 3
                
            Monkey 3:
              Starting items: 74
              Operation: new = old + 3
              Test: divisible by 17
                If true: throw to monkey 0
                If false: throw to monkey 1
        """.trimIndent()

        val itemsInspected: BigInteger = MonkeyBusinessCalculator().calculateInspectedItems(input.lines(), 20, 3)

        assertThat(itemsInspected).isEqualTo(10605)

    }

    @Test
    fun `should calculate part 1 for input`() {

        val input = ReadInputHelper.readLinesFromResource("/day11/monkeys.txt")

        val itemsInspected: BigInteger = MonkeyBusinessCalculator().calculateInspectedItems(input, 20, 3)

        assertThat(itemsInspected).isEqualTo(112221)
    }


    @Test
    fun `should execute with simple input for part 2`() {
        val input = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
                
            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0
                
            Monkey 2:
              Starting items: 79, 60, 97
              Operation: new = old * old
              Test: divisible by 13
                If true: throw to monkey 1
                If false: throw to monkey 3
                
            Monkey 3:
              Starting items: 74
              Operation: new = old + 3
              Test: divisible by 17
                If true: throw to monkey 0
                If false: throw to monkey 1
        """.trimIndent()

        val itemsInspected: BigInteger = MonkeyBusinessCalculator().calculateInspectedItems(input.lines(), 10000, 1)

        assertThat(itemsInspected).isEqualTo(10605)

    }


}
