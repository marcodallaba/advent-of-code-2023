import java.util.*
import java.util.stream.LongStream
import kotlin.math.min

class SeedFarmer(inputList: List<String>) {
    val seeds: List<Long>
    private val transformerList: MutableList<Transformer>

    init {
        seeds = Arrays.stream(inputList[0].split(": ".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()[1].split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            .map { s: String -> s.toLong() }
            .toList()
        transformerList = ArrayList()
        var startIndex = 2
        for (i in startIndex until inputList.size) {
            if (inputList[i].trim { it <= ' ' }.isEmpty() || i == inputList.size - 1) {
                transformerList.add(Transformer(inputList.subList(startIndex, i)))
                startIndex = i + 1
            }
        }
    }

    fun minimumLocation(): Long {
        return seeds.stream()
            .map { seed: Long ->
                var result = seed
                for (transformer in transformerList) {
                    result = transformer.morph(result)
                }
                result
            }
            .min { obj: Long, anotherLong: Long -> obj.compareTo(anotherLong) }
            .orElseThrow()
    }

    fun minimumLocationWithSeedRanges(): Long {
        var minimum = Long.MAX_VALUE
        var i = 0
        while (i < seeds.size) {
            minimum = min(minimum.toDouble(), minimumLocationWithSeedRanges(seeds[i], seeds[i + 1]).toDouble())
                .toLong()
            i += 2
        }
        return minimum
    }

    private fun minimumLocationWithSeedRanges(start: Long, range: Long): Long {
        return LongStream.range(start, start + range)
            .parallel()
            .map { seed: Long ->
                var result = seed
                for (transformer in transformerList) {
                    result = transformer.morph(result)
                }
                result
            }
            .boxed()
            .min { obj: Long, anotherLong: Long? -> obj.compareTo(anotherLong!!) }
            .orElseThrow()
    }

    fun getTransformerList(): List<Transformer> {
        return transformerList
    }
}