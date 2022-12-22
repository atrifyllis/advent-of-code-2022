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

    fun splitToGroups(rucksacks: List<String>): List<List<String>> {
        return rucksacks.chunked(3)
    }

    fun findBadge(group: List<String>): Char {
        return group[0].filter { group[1].contains(it) }
            .find { group[2].contains(it) } ?: '0'
    }

    fun sumBadgePriorities(rucksacks: List<String>): Int {
        return splitToGroups(rucksacks)
            .map { findBadge(it) }
            .sumOf { it.priority() }
    }


}


