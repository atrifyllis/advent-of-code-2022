package gr.alx.adventofcode.twentythree.day5

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FertilizerTest {

    @Test
    fun `should parse seeds`() {
        val input = """
            seeds: 79 14 55 13
            seed-to-soil map:
            50 98 2
            52 50 48
            """.trimIndent()
        val seeds = Fertilizer().parseSeeds(input.lines())

        assertThat(seeds).containsAll(listOf(
                Seed(79),
                Seed(14),
                Seed(55),
                Seed(13),
        ))
    }

    @Test
    fun `should parse seed-to-soil map`() {
        val input = """
            seeds: 79 14 55 13
            seed-to-soil map:
            50 98 2
            52 50 48
            """.trimIndent()
        val seedsToSoilMap = Fertilizer().parseMapByNameNew(input.lines(), "seed-to-soil")

        assertThat(seedsToSoilMap).containsExactlyInAnyOrderEntriesOf(mutableMapOf(
                Range(98L, 100L) to Range(50L, 52L),
                Range(50L, 98L) to Range(52L, 100L),

                ))
    }

    @Test
    fun `should parse almanac from sample`() {
        val input = """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48
        
        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15
        
        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4
        
        water-to-light map:
        88 18 7
        18 25 70
        
        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13
        
        temperature-to-humidity map:
        0 69 1
        1 0 69
        
        humidity-to-location map:
        60 56 37
        56 93 4
            """.trimIndent()

        val almanac = Fertilizer().parseAlmanac(input.lines())

        assertThat(almanac.seedsToSoilMap).containsExactlyInAnyOrderEntriesOf(mutableMapOf(
                Range(98L, 100L) to Range(50L, 52L),
                Range(50L, 98L) to Range(52L, 100L),
        ))

        assertThat(almanac.humidityToLocationMap).containsExactlyInAnyOrderEntriesOf(mutableMapOf(
                Range(56L, 93L) to Range(60L, 97L),
                Range(93L, 97L) to Range(56L, 60L),
        ))

        assertThat(almanac.findInMapNew(almanac.seeds[0].seed, almanac.seedsToSoilMap)).isEqualTo(81L)
        assertThat(almanac.findLocations()).containsExactlyInAnyOrder(82L, 43L, 86L, 35L)
    }

    @Test
    fun `should calculate actual input part 1`() {
        val sum = Fertilizer().calculate(ReadInputHelper.readLinesFromResource("/twentythree/day5/input.txt"))

        assertThat(sum).isEqualTo(340994526L)
    }

    @Test
    fun `should calculate actual input part 2`() {
        val sum = Fertilizer().calculate2(ReadInputHelper.readLinesFromResource("/twentythree/day5/input.txt"))

        assertThat(sum).isEqualTo(340994526L)
    }
}

