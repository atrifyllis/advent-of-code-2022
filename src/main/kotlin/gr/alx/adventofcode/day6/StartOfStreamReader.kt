package gr.alx.adventofcode.day6

class StartOfStreamReader {
    fun detectStartOfStream(stream: String, size: Int): Int {
        var acc = ""
        stream.forEachIndexed { index, value ->
            acc += value
            if (acc.length >= size && noDuplicatesInLast4Chars(acc, size)) {
                return index + 1
            }
        }
        return -1
    }

    private fun noDuplicatesInLast4Chars(acc: String, size: Int): Boolean {
        val sequence = acc.takeLast(size)
        return sequence.chars().distinct().count() == size.toLong()
    }

}
