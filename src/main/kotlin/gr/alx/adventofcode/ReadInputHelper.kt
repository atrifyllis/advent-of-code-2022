package gr.alx.adventofcode

class ReadInputHelper {
    companion object {
        fun readTextFromResource(path: String): String {
            return this::class.java.getResource(path).readText(Charsets.UTF_8)
        }

        fun readLinesFromResource(path: String): List<String> {
            return this::class.java.getResourceAsStream(path).bufferedReader().readLines()
        }

        fun splitStringToLines(input: String): List<String> {
            return input.split("\n\n")
        }
    }
}
