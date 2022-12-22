package gr.alx.adventofcode.day3

fun Char.priority(): Int {
    return this.code - (if (this.isUpperCase()) 38 else 96)
}


class RucksackCalculator {


    fun split(rucksack: String): List<String> {
        return rucksack.chunked(rucksack.length / 2)
    }

    fun findDuplicate(rucksack: String): Char {
        val compartments = split(rucksack)
        return compartments[0].find { compartments[1].contains(it) } ?: '0'
    }

    fun sumDuplicatePriorities(rucksacks: List<String>): Int {
        return rucksacks
            .map { findDuplicate(it) }
            .sumOf { it.priority() }
    }

}
