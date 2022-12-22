package gr.alx.adventofcode.day4

class CleanupCalculator {

    fun expandRangeOfSections(sections: String): List<Int> {
        val (start, end) = sections.split("-").map { it.toInt() }
        return IntRange(start, end).toList()
    }

    fun expandToSections(pairOfSections: String): List<List<Int>> {
        return pairOfSections.split(",")
            .map { expandRangeOfSections(it) }
    }

    fun hasOverlappingSectionRanges(pairOfSections: String): Boolean {
        val expandToSections: List<List<Int>> = expandToSections(pairOfSections)

        return expandToSections[0].containsAll(expandToSections[1])
                || expandToSections[1].containsAll(expandToSections[0])
    }

    fun sumUpOverlappingSectionRanges(lines: List<String>): Int {
        return lines.count { hasOverlappingSectionRanges(it) }
    }


}
