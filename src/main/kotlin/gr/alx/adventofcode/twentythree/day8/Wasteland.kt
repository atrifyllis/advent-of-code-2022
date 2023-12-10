package gr.alx.adventofcode.twentythree.day8

class Wasteland {
    fun part1(lines: List<String>): Int {
        val instructions = parseInstructions(lines)
        val nodes = parseNodes(lines)
        var currentNode = nodes.get("AAA")
        var endNodeFound = false
        var steps = 0
        while (!endNodeFound) {

            for (instr in instructions) {
//                println("current: $currentNode on step: $steps and instruction: $instr")
                steps++
                if (instr == 'L') {
                    currentNode = nodes[currentNode?.left]
                }
                if (instr == 'R') {
                    currentNode = nodes[currentNode?.right]
                }
                if (currentNode?.start == "ZZZ") {
                    endNodeFound = true
                    break
                }
            }
        }
        return steps

    }

    data class NavNode(val start: String, val left: String, val right: String)

    fun parseNodes(lines: List<String>): Map<String, NavNode> {
        return lines.drop(2)
                .map {
                    it.removeSuffix(")").split(" = (", ", ")
                }
                .associate { it[0] to NavNode(it[0], it[1], it[2]) }
//            .onEach { println(it) }

    }

    fun parseInstructions(lines: List<String>): List<Char> {
        return lines.first()
                .toList()
//            .onEach { println(it) }
    }

}
