class Transformer(inputs: List<String>) {
    val source: TYPE
    val destination: TYPE
    private val transformList: MutableList<Transform>

    init {
        val splitLine =
            inputs[0].split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].split("-".toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()
        source = TYPE.getTypeFrom(splitLine[0])
        destination = TYPE.getTypeFrom(splitLine[2])
        transformList = ArrayList()
        for (i in 1 until inputs.size) {
            val splitSpace = inputs[i].split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            transformList.add(Transform(splitSpace[0].toLong(), splitSpace[1].toLong(), splitSpace[2].toLong()))
        }
    }

    fun morph(input: Long): Long {
        val (destinationRage, sourceRange) = transformList.stream()
            .filter { (_, sourceRange, rangeLength): Transform -> sourceRange <= input && sourceRange + rangeLength > input }
            .findFirst()
            .orElse(DEFAULT_TRANSFORM)
        return input - (sourceRange - destinationRage)
    }

    fun getTransformList(): List<Transform> {
        return transformList
    }

    enum class TYPE {
        SEED,
        SOIL,
        FERTILIZER,
        WATER,
        LIGHT,
        TEMPERATURE,
        HUMIDITY,
        LOCATION;

        companion object {
            fun getTypeFrom(s: String): TYPE {
                return when (s) {
                    "seed" -> SEED
                    "soil" -> SOIL
                    "fertilizer" -> FERTILIZER
                    "water" -> WATER
                    "light" -> LIGHT
                    "temperature" -> TEMPERATURE
                    "humidity" -> HUMIDITY
                    "location" -> LOCATION
                    else -> throw IllegalArgumentException("unknown type: $s")
                }
            }
        }
    }

    @JvmRecord
    data class Transform(val destinationRage: Long, val sourceRange: Long, val rangeLength: Long)
    companion object {
        val DEFAULT_TRANSFORM = Transform(0, 0, 0)
    }
}