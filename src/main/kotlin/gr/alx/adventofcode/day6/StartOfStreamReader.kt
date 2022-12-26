package gr.alx.adventofcode.day6

class StartOfStreamReader {
    fun detectStartOfStream(stream: String): Int {
        var acc = ""
        stream.forEachIndexed { index, value ->
            acc += value
            if (acc.length >= 4 && noDuplicatesInLast4Chars(acc)) {
                return index + 1
            }
        }
        return -1
    }

    private fun noDuplicatesInLast4Chars(acc: String): Boolean {
        val sequence = acc.takeLast(4)
        return sequence.chars().distinct().count() == 4L
    }

}
