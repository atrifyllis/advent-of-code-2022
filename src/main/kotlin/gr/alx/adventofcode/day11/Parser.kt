package gr.alx.adventofcode.day11

import java.math.BigInteger

class Parser {

    companion object {
        fun toInstruction(instr: List<String>): Instruction {
            val (_, monkey) = instr[0].split(" ")
            val items: MutableList<BigInteger> = parseItems(instr[1])
            val (operation, operand) = parseOperation(instr[2])
            val test: BigInteger = parseTest(instr[3])
            val throwToTrue: BigInteger = parseTestResult(instr[4])
            val throwToFalse: BigInteger = parseTestResult(instr[5])

            return Instruction(
                monkey = monkey.removeSuffix(":").toBigInteger(),
                itemLevels = items,
                worryOperation = operation,
                worryOperand = operand,
                divisibleBy = test,
                throwToWhenTrue = throwToTrue,
                throwToWhenFalse = throwToFalse
            )

        }


        private fun parseItems(itemsLine: String): MutableList<BigInteger> {
            val (_, items) = itemsLine.split(": ")
            return items.split(", ").map { it.toBigInteger() }.toMutableList()
        }

        private fun parseOperation(multiplierLine: String): Pair<Op, BigInteger> {
            return when {
                multiplierLine.contains("old * old") -> {
                    Pair(Op.MULTI_SELF, BigInteger("-1"))
                }

                multiplierLine.contains("*") -> {
                    val (_, operand) = multiplierLine.split(" * ")
                    Pair(Op.MULTI, operand.toBigInteger())
                }

                else -> {
                    val (_, operand) = multiplierLine.split(" + ")
                    Pair(Op.ADD, operand.toBigInteger())
                }
            }
        }

        private fun parseTest(testLine: String): BigInteger {
            val (_, test) = testLine.split("by ")
            return test.toBigInteger()
        }

        private fun parseTestResult(throwToLine: String): BigInteger {
            val (_, throwTo) = throwToLine.split("monkey ")
            return throwTo.toBigInteger()
        }

    }
}
