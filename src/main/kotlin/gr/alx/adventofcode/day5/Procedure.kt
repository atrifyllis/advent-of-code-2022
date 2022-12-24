package gr.alx.adventofcode.day5

data class Procedure(val size: Int, val from: Int, val to: Int) {

    companion object {
        private fun extractSize(line: String): Int {
            return line.substringAfter("move ").substringBefore(" from").toInt()
        }

        private fun extractFrom(line: String): Int {
            return line.substringAfter("from ").substringBefore(" to").toInt()
        }

        private fun extractTo(line: String): Int {
            return line.substringAfter("to ").toInt()
        }

        fun from(line: String): Procedure {
            return Procedure(size = extractSize(line), from = extractFrom(line), to = extractTo(line))
        }
    }


}
