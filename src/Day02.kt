import java.util.Scanner
import kotlin.math.max

fun main() {

    val input = readInput("Day02")

    // firstHalf02(input)
    secondHalf02(input)
}

fun firstHalf02(input: List<String>) {

    val maxRed = 12
    val maxGreen = 13
    val maxBlue = 14

    var idSum = 0


    input.forEachIndexed { index, game ->
        val substring = game.substring(game.indexOf(':') + 1)

        var valid = true
        val sets = substring.split(';')
        sets.forEach { set ->
            val cubes = set.split(',')

            cubes.forEach {
                val cube = it.trim()
                val scanner = Scanner(cube)
                val value = scanner.nextInt()
                val color = scanner.next()
                when (color) {
                    "red" -> {
                        if (value > maxRed) {
                            valid = false
                        }
                    }

                    "green" -> {
                        if (value > maxGreen) {
                            valid = false
                        }
                    }

                    "blue" -> {
                        if (value > maxBlue) {
                            valid = false
                        }
                    }
                }
            }
        }
        if (valid) {
            idSum += (index + 1)
        }
    }

    idSum.println()
}

fun secondHalf02(input: List<String>) {

    var powersSum = 0

    input.forEachIndexed { index, game ->
        val substring = game.substring(game.indexOf(':') + 1)

        var maxRed = 0
        var maxGreen = 0
        var maxBlue = 0

        val sets = substring.split(';')
        sets.forEach { set ->
            val cubes = set.split(',')

            cubes.forEach {
                val cube = it.trim()
                val scanner = Scanner(cube)
                val value = scanner.nextInt()
                val color = scanner.next()
                when (color) {
                    "red" -> {
                        maxRed = max(maxRed, value)
                    }

                    "green" -> {
                        maxGreen = max(maxGreen, value)
                    }

                    "blue" -> {
                        maxBlue = max(maxBlue, value)
                    }
                }
            }
        }
        powersSum += (maxRed * maxGreen * maxBlue)
    }

    powersSum.println()
}