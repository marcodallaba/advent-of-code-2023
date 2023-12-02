fun main() {

    val input = readInput("Day01")

    //firstHalf(input)

    secondHalf(input)
}

fun firstHalf(input: List<String>) {
    var sum = 0L
    for (s in input) {
        val firstDigit = s.first {
            it.isDigit()
        }.digitToInt()
        val lastDigit = s.last {
            it.isDigit()
        }.digitToInt()
        sum += (firstDigit * 10 + lastDigit)
    }
    sum.println()
}

fun secondHalf(input: List<String>) {

    val map = mapOf(
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        7 to "seven",
        8 to "eight",
        9 to "nine"
    )

    var sum = 0L
    for (s in input) {
        val firstDigitIndex = s.indexOfFirst {
            it.isDigit()
        }
        var firstStringDigitIndex = -1
        var firstNumber = 0
        for (pair in map) {
            val indexOf = s.indexOf(pair.value)
            if (indexOf != -1) {
                if (firstStringDigitIndex == -1) {
                    firstNumber = pair.key
                    firstStringDigitIndex = indexOf
                } else {
                    if (indexOf < firstStringDigitIndex) {
                        firstNumber = pair.key
                        firstStringDigitIndex = indexOf
                    }
                }
            }
        }

        val lastDigitIndex = s.indexOfLast {
            it.isDigit()
        }

        var lastStringDigitIndex = -1
        var lastNumber = 0
        for (pair in map) {
            val indexOf = s.lastIndexOf(pair.value)
            if (indexOf != -1) {
                if (lastStringDigitIndex == -1) {
                    lastNumber = pair.key
                    lastStringDigitIndex = indexOf
                } else {
                    if (indexOf > lastStringDigitIndex) {
                        lastNumber = pair.key
                        lastStringDigitIndex = indexOf
                    }
                }
            }
        }

        sum += when {
            firstDigitIndex != -1 && firstStringDigitIndex != -1 -> {
                if (firstDigitIndex < firstStringDigitIndex) {
                    s[firstDigitIndex].digitToInt() * 10
                } else {
                    firstNumber * 10
                }
            }

            firstDigitIndex != -1 -> {
                s[firstDigitIndex].digitToInt() * 10
            }

            else -> {
                firstNumber * 10
            }
        }
        sum.println()

        sum += when {
            lastDigitIndex != -1 && lastStringDigitIndex != -1 -> {
                if (lastDigitIndex > lastStringDigitIndex) {
                    s[lastDigitIndex].digitToInt()
                } else {
                    lastNumber
                }
            }

            lastDigitIndex != -1 -> {
                s[lastDigitIndex].digitToInt()
            }

            else -> {
                lastNumber
            }
        }
        sum.println()
    }
    sum.println()
}

