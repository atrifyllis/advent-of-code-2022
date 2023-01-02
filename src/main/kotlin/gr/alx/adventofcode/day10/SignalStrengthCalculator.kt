package gr.alx.adventofcode.day10

class SignalStrengthCalculator {
    fun parse(lines: List<String>): Signal {

        val signal = Signal()

        lines.forEach {
            val split = it.split(" ")
            when (split[0]) {
                "noop" -> signal.increase(signal = 0, cycles = 1)
                "addx" -> signal.increase(signal = split[1].toLong(), cycles = 2)
            }
        }

        return signal
    }

    fun calculateStrength(lines: List<String>): Long {
        val signal: Signal = parse(lines)
        var total: Long = 0
        for (i in 20..220 step 40) {
            total += i * signal.map[i.toLong()]!!
        }
        return total
    }

}

data class Signal(
    var x: Long = 1,
    var cycle: Long = 0,
    val map: MutableMap<Long, Long> = mutableMapOf<Long, Long>(1L to 1L),
) {
    fun increase(signal: Long, cycles: Long) {
        for (c in 1..cycles) {
            val cNum = cycle + c
            map[cNum] = x
        }
        x += signal
        cycle += cycles
    }
}
