package gr.alx.adventofcode.twentythree.day5

class Fertilizer {

    fun calculate(lines: List<String>): Long {
        return parseAlmanac(lines)
                .findLocations()
                .min()
    }

    fun calculate2(lines: List<String>): Long {
        return parseAlmanacRange(lines)
                .findLocations()
                .min()
    }

    fun parseAlmanac(lines: List<String>): Almanac {
        return Almanac(
                parseSeeds(lines),
                parseMapByNameNew(lines, "seed-to-soil"),
                parseMapByNameNew(lines, "soil-to-fertilizer"),
                parseMapByNameNew(lines, "fertilizer-to-water"),
                parseMapByNameNew(lines, "water-to-light"),
                parseMapByNameNew(lines, "light-to-temperature"),
                parseMapByNameNew(lines, "temperature-to-humidity"),
                parseMapByNameNew(lines, "humidity-to-location"),
        )
    }

    fun parseAlmanacRange(lines: List<String>): AlmanacRange {
        return AlmanacRange(
                parseSeedsRange(lines),
                parseMapByNameNew(lines, "seed-to-soil"),
                parseMapByNameNew(lines, "soil-to-fertilizer"),
                parseMapByNameNew(lines, "fertilizer-to-water"),
                parseMapByNameNew(lines, "water-to-light"),
                parseMapByNameNew(lines, "light-to-temperature"),
                parseMapByNameNew(lines, "temperature-to-humidity"),
                parseMapByNameNew(lines, "humidity-to-location"),
        )
    }

    fun parseSeeds(lines: List<String>): List<Seed> {
        return toLineOfNumbers(lines[0].substringAfter(':'))
                .map { Seed(it) }
    }

    fun parseSeedsRange(lines: List<String>): Sequence<Seed> {
        return toLineOfNumbers(lines[0].substringAfter(':'))
                .asSequence()
                .windowed(2)
                .map { it.first()..it.last() }
                .flatMap { range -> range.map { Seed(it) } }

    }


    fun parseMapByNameNew(lines: List<String>, mapName: String): Map<Range, Range> {
        return lines.asSequence()
                .dropWhile { !it.contains(mapName) }
                .drop(1) // drop the 'title' line
                .takeWhile { it.isNotBlank() }
                .map { line -> toLineOfNumbers(line) }
                .associate {
                    Range(it[1], it[1] + it[2]) to Range(it[0], it[0] + it[2])
                }
    }


    private fun toLineOfNumbers(line: String) = line
            .split(' ')
            .filter { it.isNotBlank() }
            .map { it.toLong() }
}

data class Range(val start: Long, val end: Long) {
    fun contains(number: Long): Boolean {
        return number in start..end
    }

    fun indexOf(number: Long): Long {
        return number - start
    }
}

data class Seed(val seed: Long)
data class Almanac(
        val seeds: List<Seed>,
        val seedsToSoilMap: Map<Range, Range>,
        val soilToFertilizerMap: Map<Range, Range>,
        val fertilizerToWaterMap: Map<Range, Range>,
        val waterToLightMap: Map<Range, Range>,
        val lightToTemperatureMap: Map<Range, Range>,
        val temperatureToHumidityMap: Map<Range, Range>,
        val humidityToLocationMap: Map<Range, Range>
) {

    fun findInMapNew(number: Long, map: Map<Range, Range>): Long {
        val entry: Map.Entry<Range, Range>? = map.entries.find { it.key.contains(number) }
        return entry?.key?.indexOf(number)?.let { entry.value.start + it } ?: number
    }

    fun findLocations(): List<Long> {

        return seeds.map {
            findInMapNew(
                    findInMapNew(
                            findInMapNew(
                                    findInMapNew(
                                            findInMapNew(
                                                    findInMapNew(
                                                            findInMapNew(
                                                                    it.seed,
                                                                    seedsToSoilMap
                                                            ),
                                                            soilToFertilizerMap
                                                    ),
                                                    fertilizerToWaterMap
                                            ),
                                            waterToLightMap
                                    ),
                                    lightToTemperatureMap
                            ),
                            temperatureToHumidityMap
                    ),
                    humidityToLocationMap
            )
        }
    }
}

data class AlmanacRange(
        val seeds: Sequence<Seed>,
        val seedsToSoilMap: Map<Range, Range>,
        val soilToFertilizerMap: Map<Range, Range>,
        val fertilizerToWaterMap: Map<Range, Range>,
        val waterToLightMap: Map<Range, Range>,
        val lightToTemperatureMap: Map<Range, Range>,
        val temperatureToHumidityMap: Map<Range, Range>,
        val humidityToLocationMap: Map<Range, Range>
) {

    fun findInMapNew(number: Long, map: Map<Range, Range>): Long {
        val entry: Map.Entry<Range, Range>? = map.entries.find { it.key.contains(number) }
        return entry?.key?.indexOf(number)?.let { entry.value.start + it } ?: number
    }

    fun findLocations(): Sequence<Long> {

        return seeds.map {
            findInMapNew(
                    findInMapNew(
                            findInMapNew(
                                    findInMapNew(
                                            findInMapNew(
                                                    findInMapNew(
                                                            findInMapNew(
                                                                    it.seed,
                                                                    seedsToSoilMap
                                                            ),
                                                            soilToFertilizerMap
                                                    ),
                                                    fertilizerToWaterMap
                                            ),
                                            waterToLightMap
                                    ),
                                    lightToTemperatureMap
                            ),
                            temperatureToHumidityMap
                    ),
                    humidityToLocationMap
            )
        }
    }
}
