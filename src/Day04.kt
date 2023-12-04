import java.util.stream.IntStream.range
import kotlin.math.pow

fun main() {

    val input = readInput("Day04")

    //firstHalf04(input)

    secondHalf04(input)
}

fun firstHalf04(input: List<String>) {

    var sum = 0
    input.forEach {
        val split = it.substring(it.indexOf(':') + 1).split('|')
        val winningNumbers = split[0].trim().split("\\s+".toRegex()).map {
            it.toInt()
        }.toSet()
        val myNumbers = split[1].trim().split("\\s+".toRegex()).map {
            it.toInt()
        }

        val myWinningNumbersCount = myNumbers.count {
            winningNumbers.contains(it)
        }

        if (myWinningNumbersCount > 0) {
            sum += 2.0.pow(myWinningNumbersCount - 1).toInt()
        }
    }
    sum.println()
}

fun secondHalf04(input: List<String>) {

    val count = IntArray(input.size) {
        1
    }

    input.forEachIndexed { index, it ->
        val split = it.substring(it.indexOf(':') + 1).split('|')
        val winningNumbers = split[0].trim().split("\\s+".toRegex()).map {
            it.toInt()
        }.toSet()
        val myNumbers = split[1].trim().split("\\s+".toRegex()).map {
            it.toInt()
        }

        val myWinningNumbersCount = myNumbers.count {
            winningNumbers.contains(it)
        }

        if (myWinningNumbersCount > 0) {
            val currentCount = count[index]

            for (i in index + 1..index + myWinningNumbersCount) {
                if (i < count.size) {
                    count[i] += currentCount
                }
            }
        }
    }

    count.sum().println()

}
