package gr.alx.adventofcode.twentythree.day9

class Oasis {
    fun part1(lines: List<String>): Int {
        return lines.map { parseLine(it) }
                .map {
                    listOfDifferences(listOf(it))
                }
                .onEach { it.onEach { println(it) } }
                .map {
                    it.reversed()
                }
                .onEach { it.onEach { println(it) } }
                .map {
                    it.fold(0) { acc, ints -> acc + ints.last() }
                }
                .onEach { println(it) }
                .sum()
    }

    fun part2(lines: List<String>): Any {
        return lines.map { parseLine(it) }
                .map {
                    listOfDifferences(listOf(it))
                }
                .onEach { it.onEach { println(it) } }
                .map {
                    it.reversed()
                }
                .onEach { it.onEach { println(it) } }
                .map {
                    it.fold(0) { acc, ints -> ints.first() - acc }
                }
                .onEach { println(it) }
                .sum()
    }

    private fun listOfDifferences(line: List<List<Int>>): List<List<Int>> {
        val map: List<Int> = line.last().windowed(2).map { it[1] - it[0] }
        if (map.all { it == 0 }) {
            return line + listOf(map)
        }
        return listOfDifferences(line + listOf(map))
    }

    private fun parseLine(line: String): List<Int> {
        return line.split(' ').map { it.trim().toInt() }
    }

}

