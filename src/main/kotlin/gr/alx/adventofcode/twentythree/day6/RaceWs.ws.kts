import gr.alx.adventofcode.ReadInputHelper

fun part1() {

    val lines: List<String> = ReadInputHelper.readLinesFromResource("/twentythree/day6/input.txt")

    val parsedLines: List<List<String>> = lines
            .map {
                it.substringAfter(":")
                        .trim()
                        .split(' ')
                        .filter { it.isNotBlank() }
            }
            .onEach(::println)

    parsedLines[0].zip(parsedLines[1])
            .map { Race(it.first.toLong(), it.second.toLong()) }
            .map { it.calculateWins() }
            .reduce(Int::times)
            .let { println(it) }
}

fun part2() {
    val lines: List<String> = ReadInputHelper.readLinesFromResource("/twentythree/day6/input.txt")

    val parsedLines: List<String> = lines
            .map {
                it.substringAfter(":")
                        .trim()
                        .filterNot { it.isWhitespace() }
            }
            .onEach { println(it) }


    Race(parsedLines[0].toLong(), parsedLines[1].toLong())
            .calculateWins()
            .let { println(it) }
}

part2()

data class Race(val time: Long, val distance: Long) {
    fun calculateWins(): Int {
        println(time)
        println(distance)
        var wins = 0
        for (speed in 1 until time) {
            val remainingTime = time - speed
            val distanceCovered = remainingTime * speed
            if (distanceCovered > distance) {
                wins++
            }
        }
        return wins
    }
}
