package gr.alx.adventofcode.day6

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StartOfStreamReaderTest {

    @Test
    fun `should detect four consecutive distinct letters`() {
        val input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"

        val indexOfStartOfStream: Int = StartOfStreamReader().detectStartOfStream(input, 4)

        assertThat(indexOfStartOfStream).isEqualTo(7)
    }

    @Test
    fun `should read input`() {
        val stream = ReadInputHelper.readTextFromResource("/day6/stream.txt")

        val indexOfStartOfStream: Int = StartOfStreamReader().detectStartOfStream(stream, 4)

        assertThat(indexOfStartOfStream).isEqualTo(1198)

    }

    @Test
    fun `should read input for part 2`() {
        val stream = ReadInputHelper.readTextFromResource("/day6/stream.txt")

        val indexOfStartOfStream: Int = StartOfStreamReader().detectStartOfStream(stream, 14)

        assertThat(indexOfStartOfStream).isEqualTo(7)

    }
}
