package gr.alx.adventofcode.day3

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RucksackTest {


    @Test
    fun `should split rucksack in two compartments`() {

        val halves: List<String> = RucksackCalculator().split("vJrwpWtwJgWrhcsFMMfFFhFp")

        assertThat(halves).hasSize(2)
        assertThat(halves[0]).isEqualTo("vJrwpWtwJgWr")
        assertThat(halves[1]).isEqualTo("hcsFMMfFFhFp")
    }

    @Test
    fun `should find duplicate in compartments`() {

        val duplicateChar: Char = RucksackCalculator().findDuplicate("vJrwpWtwJgWrhcsFMMfFFhFp")

        assertThat(duplicateChar).isEqualTo('p')
    }

    @Test
    fun `should find duplicate's priority'`() {

        val duplicateChar: Char = RucksackCalculator().findDuplicate("vJrwpWtwJgWrhcsFMMfFFhFp")

        assertThat(duplicateChar.priority()).isEqualTo(16)
    }

    @Test
    fun `should find sum of duplicate priorities`() {
        val rucksacks = ReadInputHelper.readLinesFromResource("/day3/rucksack.txt")

        val sum = RucksackCalculator().sumDuplicatePriorities(rucksacks)

        assertThat(sum).isEqualTo(7821)
    }

    @Test
    fun `should split to groups of three`() {
        val rucksacks = """
           vJrwpWtwJgWrhcsFMMfFFhFp
           jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
           PmmdzqPrVvPwwTWBwg
           wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
           ttgJtRGJQctTZtZT
           CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent()

        val groups: List<List<String>> = RucksackCalculator().splitToGroups(rucksacks.lines())

        assertThat(groups).hasSize(2)

        assertThat(groups[0]).hasSize(3)
        assertThat(groups[0]).containsExactly("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg")


        assertThat(groups[1]).hasSize(3)
        assertThat(groups[1]).containsExactly("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw")
    }

    @Test
    fun `should find badge item in group`() {
        val item: Char = RucksackCalculator().findBadge(listOf("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg"))

        assertThat(item).isEqualTo('r')
    }

    @Test
    fun `should sum badge priorities`() {
        val rucksacks = ReadInputHelper.readLinesFromResource("/day3/rucksack.txt")

        val sum: Int = RucksackCalculator().sumBadgePriorities(rucksacks)

        assertThat(sum).isEqualTo(2752)
    }
}


