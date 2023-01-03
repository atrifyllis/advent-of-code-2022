package gr.alx.adventofcode.day11

class MonkeyBusinessCalculator {
    fun parse(lines: List<String>): List<Instruction> {
        return lines
            .filter { it.trim().isNotEmpty() }
            .chunked(6)
            .map { Parser.toInstruction(it) }

    }

    fun calculateInspectedItems(lines: List<String>, rounds: Int): Long {
        val itemsPerMonkey: MutableMap<Long, MutableList<Long>> = mutableMapOf()
        var inspectedPerMonkey: MutableMap<Long, Long> = mutableMapOf()
        val instructions = parse(lines)
        instructions.forEach {
            itemsPerMonkey[it.monkey] = it.itemLevels
        }
        repeat(rounds) {
            inspectedPerMonkey = calculateRound(instructions, itemsPerMonkey, inspectedPerMonkey).second
        }
        return inspectedPerMonkey.values
            .sortedDescending()
            .take(2)
            .reduce { acc, i -> acc * i }
    }


    fun calculate(lines: List<String>, rounds: Int): Map<Long, List<Long>> {
        var itemsPerMonkey: MutableMap<Long, MutableList<Long>> = mutableMapOf()
        val inspectedPerMonkey: MutableMap<Long, Long> = mutableMapOf()
        val instructions = parse(lines)
        instructions.forEach {
            itemsPerMonkey[it.monkey] = it.itemLevels
        }
        repeat(rounds) {
            itemsPerMonkey = calculateRound(instructions, itemsPerMonkey, inspectedPerMonkey).first
        }
        return itemsPerMonkey
    }


    private fun calculateRound(
        instructions: List<Instruction>,
        itemsPerMonkey: MutableMap<Long, MutableList<Long>>,
        inspectedPerMonkey: MutableMap<Long, Long>,
    ): Pair<MutableMap<Long, MutableList<Long>>, MutableMap<Long, Long>> {
        instructions.forEach { instruction ->
            val currentMonkey = instruction.monkey
            val currentItems = itemsPerMonkey[currentMonkey]
            currentItems?.forEach { itemLevel ->
                inspectedPerMonkey[currentMonkey] = (inspectedPerMonkey[currentMonkey] ?: 0) + 1
                val newWorryLevel: Long = instruction.calculateWorryLevel(itemLevel)
                val monkeyToThrowTo: Long = instruction.calculateMonkeyToThrowTo(newWorryLevel)
                itemsPerMonkey[monkeyToThrowTo]?.add(newWorryLevel)
            }
            // empty current monkey list
            itemsPerMonkey[currentMonkey] = mutableListOf()
        }
        return Pair(itemsPerMonkey, inspectedPerMonkey)
    }
}


data class Instruction(
    val monkey: Long,
    val itemLevels: MutableList<Long>,
    val worryOperand: Long,
    val worryOperation: Op,
    val divisibleBy: Long,
    val throwToWhenTrue: Long,
    val throwToWhenFalse: Long,

    ) {
    fun calculateWorryLevel(itemLevel: Long): Long {
        val worryLevelAfterInspection = worryOperation.performOperation(itemLevel, worryOperand)
        return Math.floorDiv(worryLevelAfterInspection, 3)
    }

    fun calculateMonkeyToThrowTo(newWorryLevel: Long): Long {
        return if (newWorryLevel.toInt() % divisibleBy.toInt() == 0) throwToWhenTrue else throwToWhenFalse
    }
}

enum class Op {
    ADD, MULTI, MULTI_SELF;

    fun performOperation(itemLevel: Long, worryOperand: Long): Long {
        return when (this) {
            MULTI -> itemLevel * worryOperand
            ADD -> itemLevel + worryOperand
            MULTI_SELF -> itemLevel * itemLevel
        }
    }
}


