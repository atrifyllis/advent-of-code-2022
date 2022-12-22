package gr.alx.adventofcode.day1

import gr.alx.adventofcode.day1.TestInput.Companion.CALORIES_PER_ELF
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CaloriesCalculatorTest {
    @Test
    fun `should read one elf with one calorie entry and return it`() {
        val caloriesPerElf = """
            1
        """.trimIndent()

        val calculator = CaloriesCalculator()

        val maxTotalCalories: Int = calculator.calculateMaxCalories(caloriesPerElf)

        assertThat(maxTotalCalories).isEqualTo(1)
    }

    @Test
    fun `should read one elf with two calorie entries and return sum of them`() {
        val caloriesPerElf = """
            1
            2
        """.trimIndent()

        val calculator = CaloriesCalculator()

        val maxTotalCalories: Int = calculator.calculateMaxCalories(caloriesPerElf)

        assertThat(maxTotalCalories).isEqualTo(3)
    }

    @Test
    fun `should read two elves with one calorie entry each and return max of them`() {
        val caloriesPerElf = """
            1
            
            2
        """.trimIndent()

        val calculator = CaloriesCalculator()

        val maxTotalCalories: Int = calculator.calculateMaxCalories(caloriesPerElf)

        assertThat(maxTotalCalories).isEqualTo(2)
    }

    @Test
    fun `should read two elves with two calorie entries each and return max of sum`() {
        val caloriesPerElf = """
            1
            2
            
            2
            3
        """.trimIndent()

        val calculator = CaloriesCalculator()

        val maxTotalCalories: Int = calculator.calculateMaxCalories(caloriesPerElf)

        assertThat(maxTotalCalories).isEqualTo(5)
    }

    @Test
    fun `should read small subset of input and calculate correct max`() {
        val caloriesPerElf = """
            18814

            1927
            12782
            8734
            10904
            9548
            1493
            
            4576
            4235
            2617
            1012
            2088
            1325
            1249
            5173
            4893
            3295
            2376
            2714
            6210
            
            6684
            5766
            3442
            4901
            4875
            4815
            5898
            2410
            5789
            4133
            3590
            1342
        """.trimIndent()

        val calculator = CaloriesCalculator()

        val maxTotalCalories: Int = calculator.calculateMaxCalories(caloriesPerElf)

        assertThat(maxTotalCalories).isEqualTo(53645)
    }

    @Test
    fun `should read input and calculate correct max`() {

        val calculator = CaloriesCalculator()

        val maxTotalCalories: Int = calculator.calculateMaxCalories(CALORIES_PER_ELF)

        assertThat(maxTotalCalories).isEqualTo(69626)
    }

    @Test
    fun `should sum two elves `() {
        val caloriesPerElf = """
            1
            
            2
        """.trimIndent()

        val calculator = CaloriesCalculator()

        val sum: Int = calculator.retrieveSumOfFirstThreeMaxCalories(caloriesPerElf)

        assertThat(sum).isEqualTo(3)

    }

    @Test
    fun `should sum two elves with two calorie entries`() {
        val caloriesPerElf = """
            1
            2
            
            2
            3
        """.trimIndent()

        val calculator = CaloriesCalculator()

        val sum: Int = calculator.retrieveSumOfFirstThreeMaxCalories(caloriesPerElf)

        assertThat(sum).isEqualTo(8)

    }

    @Test
    fun `should read input and sum 3 top elves`() {

        val calculator = CaloriesCalculator()

        val sum = calculator.retrieveSumOfFirstThreeMaxCalories(CALORIES_PER_ELF)

        assertThat(sum).isEqualTo(206780)
    }
}
