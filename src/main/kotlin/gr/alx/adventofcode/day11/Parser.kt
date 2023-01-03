package gr.alx.adventofcode.day11

class Parser {

    companion object {
        fun toInstruction(instr: List<String>): Instruction {
            val (_, monkey) = instr[0].split(" ")
            val items: MutableList<Long> = parseItems(instr[1])
            val (operation, operand) = parseOperation(instr[2])
            val test: Long = parseTest(instr[3])
            val throwToTrue: Long = parseTestResult(instr[4])
            val throwToFalse: Long = parseTestResult(instr[5])

            return Instruction(
                monkey = monkey.removeSuffix(":").toLong(),
                itemLevels = items,
                worryOperation = operation,
                worryOperand = operand,
                divisibleBy = test,
                throwToWhenTrue = throwToTrue,
                throwToWhenFalse = throwToFalse
            )

        }


        private fun parseItems(itemsLine: String): MutableList<Long> {
            val (_, items) = itemsLine.split(": ")
            return items.split(", ").map { it.toLong() }.toMutableList()
        }

        private fun parseOperation(multiplierLine: String): Pair<Op, Long> {
            return when {
                multiplierLine.contains("old * old") -> {
                    Pair(Op.MULTI_SELF, -1)
                }

                multiplierLine.contains("*") -> {
                    val (_, operand) = multiplierLine.split(" * ")
                    Pair(Op.MULTI, operand.toLong())
                }

                else -> {
                    val (_, operand) = multiplierLine.split(" + ")
                    Pair(Op.ADD, operand.toLong())
                }
            }
        }

        private fun parseTest(testLine: String): Long {
            val (_, test) = testLine.split("by ")
            return test.toLong()
        }

        private fun parseTestResult(throwToLine: String): Long {
            val (_, throwTo) = throwToLine.split("monkey ")
            return throwTo.toLong()
        }

    }
}
