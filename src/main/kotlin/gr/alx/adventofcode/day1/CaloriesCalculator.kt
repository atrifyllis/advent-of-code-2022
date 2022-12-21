package gr.alx.adventofcode.day1

class CaloriesCalculator {

    fun calculateMaxCalories(caloriesPerElf: String): Int {
        return caloriesPerElf
            .split("\n\n")
            .map { entries ->
                entries.lines()
                    .filter { it.isNotEmpty() }
                    .sumOf { it.toInt() }
            }
            .max()
    }
}
