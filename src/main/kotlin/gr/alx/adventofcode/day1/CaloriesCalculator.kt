package gr.alx.adventofcode.day1

class CaloriesCalculator {

    fun calculateMaxCalories(caloriesPerElf: String): Int {
        return splitCaloriesPerElf(caloriesPerElf)
            .map { mapEntriesToSums(it) }
            .max()
    }

    fun retrieveSumOfFirstThreeMaxCalories(caloriesPerElf: String): Int {
        return splitCaloriesPerElf(caloriesPerElf)
            .map { mapEntriesToSums(it) }
            .sortedDescending()
            .take(3)
            .sum()

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
