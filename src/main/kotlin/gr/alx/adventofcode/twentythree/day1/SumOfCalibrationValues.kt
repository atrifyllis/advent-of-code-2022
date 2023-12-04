package gr.alx.adventofcode.twentythree.day1

class SumOfCalibrationValues {
    private var lineNo: Int = 0

    companion object {
        val NUMBERS: Map<String, String> = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )
    }

    fun calculate(lines: List<String>): Int {
        var sum = 0
        return lines.map { sumLine(it) }.sumOf { it }
    }

    private fun sumLine(line: String): Int {
        var firstDigit = ""
        var lastDigit = ""
        for (i in line) {
            if (i.isDigit()) {
                if (firstDigit.isBlank()) {
                    firstDigit = i.toString()
                } else {
                    lastDigit = i.toString()
                }
            }


        }
        if (lastDigit.isBlank()) {
            lastDigit = firstDigit
        }
        return Integer.valueOf(firstDigit + lastDigit)
    }

    fun calculatePart2(lines: List<String>): Int {
        lineNo = 1
        var sum = 0
        return lines.map { sumLinePart2(it) }.sumOf { it }
    }

    private fun sumLinePart2(line: String): Int {
        var firstDigit = ""
        var lastDigit = ""
        var potentialDigit = ""

        for (i in line.indices) {
            if (line[i].isDigit()) {
                if (firstDigit.isBlank()) {
                    firstDigit = line[i].toString()
                } else {
                    lastDigit = line[i].toString()
                }
                println("current digit: $firstDigit$lastDigit")
                potentialDigit = "" // reset potential digit
            } else {
                potentialDigit += line[i].toString()
                if (isDigit(potentialDigit)) {
                    if (firstDigit.isBlank()) {
                        firstDigit = NUMBERS[potentialDigit]!!
                    } else {
                        lastDigit = NUMBERS[potentialDigit]!!
                    }
                    println("current digit: $firstDigit$lastDigit")
                    potentialDigit = "" // reset potential digit
                } else if (!isSubstringOfDigit(potentialDigit)) {
                    potentialDigit =
                        potentialDigit.drop(1) // trim start of potential digit, so next time we compare with the rest of it
                }
            }
        }
        if (lastDigit.isBlank()) {
            lastDigit = firstDigit
        }
        println("line $lineNo actual digit: ######## $firstDigit$lastDigit ########")
        lineNo++
        return Integer.valueOf(firstDigit + lastDigit)

    }

    private fun isSubstringOfDigit(potentialDigit: String): Boolean {
        return NUMBERS.any { it.key.startsWith(potentialDigit) }
    }

    private fun isDigit(potentialDigit: String): Boolean {
        println("potential digit: $potentialDigit")
        return NUMBERS.contains(potentialDigit)
    }

}

