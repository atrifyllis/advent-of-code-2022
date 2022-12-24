package gr.alx.adventofcode.day5

import java.util.*


class CratesArranger {

    //    fun readInput(lines: List<String>) {
//        val crateLines = lines.subList(0, lines.indexOf("")).reversed()
//        val procedureLines = lines.subList(lines.indexOf("")+ 1 , lines.size + 1)
//
//    }
    fun readStacks(lines: List<String>): MutableMap<Int, Deque<Char>> {
        val stacks = mutableMapOf<Int, Deque<Char>>()
        val lineIndexToStackIndex = mutableMapOf<Int, Int>()
        val crateLines = lines.reversed()

        crateLines.forEach { line ->
            line.forEachIndexed { charIndex, char ->
                if (isValid(char)) {
                    when {
                        char.isDigit() -> {
                            stacks[char.digitToInt()] = ArrayDeque()
                            lineIndexToStackIndex[charIndex] = char.digitToInt()
                        }

                        char.isLetter() -> {
                            stacks[lineIndexToStackIndex[charIndex]]?.push(char)
                        }
                    }

                }
            }
        }
        return stacks
    }

    private fun isValid(char: Char) = !char.isWhitespace() && char != '[' && char != ']'
    fun readProcedureLines(lines: List<String>): List<Procedure> {
        return lines.map {
            Procedure(size = extractSize(it), from = extractFrom(it), to = extractTo(it))
        }
    }

    private fun extractFrom(line: String): Int {
        return line.substringAfter("from ").substringBefore(" to").toInt()
    }

    private fun extractTo(line: String): Int {
        return line.substringAfter("to ").toInt()
    }

    private fun extractSize(line: String): Int {
        return line.substringAfter("move ").substringBefore(" from").toInt()
    }

    fun execute(lines: List<String>): String {

        val crateLines = lines.subList(0, lines.indexOf(""))
        val procedureLines = lines.subList(lines.indexOf("") + 1, lines.size)

        val stacks = applyAll(readStacks(crateLines), readProcedureLines(procedureLines))

        return stacks.toSortedMap().values.map {
            it.peek()
        }.joinToString("")

    }

    private fun applyAll(stacks: MutableMap<Int, Deque<Char>>, procedures: List<Procedure>): MutableMap<Int, Deque<Char>> {
        procedures.forEach {
            applyProcedure(it, stacks)
        }
        return stacks
    }

    private fun applyProcedure(procedure: Procedure, stacks: MutableMap<Int, Deque<Char>>) {
        for (i in 1..procedure.size) {
            val crate = stacks[procedure.from]?.pop()
            stacks[procedure.to]?.push(crate)
        }
    }

    fun executePart2(lines: List<String>): String {

        val crateLines = lines.subList(0, lines.indexOf(""))
        val procedureLines = lines.subList(lines.indexOf("") + 1, lines.size)

        val stacks = applyAllPart2(readStacks(crateLines), readProcedureLines(procedureLines))

        return stacks.toSortedMap().values.map {
            it.peek()
        }.joinToString("")
    }

    private fun applyAllPart2(stacks: MutableMap<Int, Deque<Char>>, procedures: List<Procedure>): MutableMap<Int, Deque<Char>> {
        procedures.forEach {
            applyPart2Procedure(it, stacks)
        }
        return stacks
    }

    private fun applyPart2Procedure(procedure: Procedure, stacks: MutableMap<Int, Deque<Char>>) {

        val temp = mutableListOf<Char>()
        for (i in 1..procedure.size) {
            temp.add(stacks[procedure.from]?.pop()!!)

        }
        temp.reversed().forEach {
            stacks[procedure.to]?.push(it)
        }
    }


}
