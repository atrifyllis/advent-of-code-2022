package gr.alx.adventofcode.day1

class CaloriesCalculator {

    fun calculateMaxCalories(caloriesPerElf: String): Int {
        return splitCaloriesPerElf(caloriesPerElf)
            .map { mapEntriesToSums(it) }
            .max()
    }

    fun mapCalorieEntriesPerElfToSums(caloriesPerElf: String): List<Int> {
        return splitCaloriesPerElf(caloriesPerElf)
            .map { mapEntriesToSums(it) }
            .sortedDescending()

    }

    private fun splitCaloriesPerElf(caloriesPerElf: String): List<String> {
        return caloriesPerElf.split("\n\n")
    }

    private fun mapEntriesToSums(it: String): Int {
        return it.lines()
            .filter { it.isNotEmpty() }
            .sumOf { it.toInt() }
    }
}
