package gr.alx.adventofcode.day11

import java.math.BigInteger

class MonkeyBusinessCalculator {
    fun parse(lines: List<String>): List<Instruction> {
        return lines
            .filter { it.trim().isNotEmpty() }
            .chunked(6)
            .map { Parser.toInstruction(it) }

    }

    fun calculateInspectedItems(lines: List<String>, rounds: Int, divisor: Long): BigInteger {
        val itemsPerMonkey: MutableMap<BigInteger, MutableList<BigInteger>> = mutableMapOf()
        var inspectedPerMonkey: MutableMap<BigInteger, BigInteger> = mutableMapOf()
        val instructions = parse(lines)
        instructions.forEach {
            itemsPerMonkey[it.monkey] = it.itemLevels
        }
        repeat(rounds) {
            inspectedPerMonkey = calculateRound(instructions, itemsPerMonkey, inspectedPerMonkey, divisor).second
        }
        return inspectedPerMonkey.values
            .sortedDescending()
            .take(2)
            .reduce { acc, i -> acc * i }
    }


    fun calculate(lines: List<String>, rounds: Int): Map<BigInteger, List<BigInteger>> {
        var itemsPerMonkey: MutableMap<BigInteger, MutableList<BigInteger>> = mutableMapOf()
        val inspectedPerMonkey: MutableMap<BigInteger, BigInteger> = mutableMapOf()
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
        itemsPerMonkey: MutableMap<BigInteger, MutableList<BigInteger>>,
        inspectedPerMonkey: MutableMap<BigInteger, BigInteger>,
        divisor: Long = 3,
    ): Pair<MutableMap<BigInteger, MutableList<BigInteger>>, MutableMap<BigInteger, BigInteger>> {
        instructions.forEach { instruction ->
            val currentMonkey = instruction.monkey
            val currentItems = itemsPerMonkey[currentMonkey]
            currentItems?.forEach { itemLevel ->
                inspectedPerMonkey[currentMonkey] = (inspectedPerMonkey[currentMonkey] ?: BigInteger.ZERO) + BigInteger.ONE
                val newWorryLevel: BigInteger = instruction.calculateWorryLevel(itemLevel, divisor)
                val monkeyToThrowTo: BigInteger = instruction.calculateMonkeyToThrowTo(newWorryLevel)
                itemsPerMonkey[monkeyToThrowTo]?.add(newWorryLevel)
            }
            // empty current monkey list
            itemsPerMonkey[currentMonkey] = mutableListOf()
        }
        return Pair(itemsPerMonkey, inspectedPerMonkey)
    }
}


data class Instruction(
    val monkey: BigInteger,
    val itemLevels: MutableList<BigInteger>,
    val worryOperand: BigInteger,
    val worryOperation: Op,
    val divisibleBy: BigInteger,
    val throwToWhenTrue: BigInteger,
    val throwToWhenFalse: BigInteger,

    ) {
    fun calculateWorryLevel(itemLevel: BigInteger, divisor: Long): BigInteger {
        val worryLevelAfterInspection = worryOperation.performOperation(itemLevel, worryOperand)
        val floorDiv = floorDiv(worryLevelAfterInspection, BigInteger.valueOf(divisor))
//        println("$worryLevelAfterInspection / ${BigInteger.valueOf(divisor)} = $floorDiv")
        return floorDiv
    }

    fun calculateMonkeyToThrowTo(newWorryLevel: BigInteger): BigInteger {
        return if (newWorryLevel.toInt() % divisibleBy.toInt() == 0) throwToWhenTrue else throwToWhenFalse
    }

    fun floorDiv(a: BigInteger, b: BigInteger): BigInteger {
        // divideAndRemainder returns quotient and remainder in array
        val qr = a.divideAndRemainder(b)
        return if (qr[0].signum() >= 0 || qr[1].signum() == 0) qr[0] else qr[0].subtract(BigInteger.ONE)
    }
}

enum class Op {
    ADD, MULTI, MULTI_SELF;

    fun performOperation(itemLevel: BigInteger, worryOperand: BigInteger): BigInteger {
        return when (this) {
            MULTI -> itemLevel * worryOperand
            ADD -> itemLevel + worryOperand
            MULTI_SELF -> itemLevel * itemLevel
        }
    }
}


