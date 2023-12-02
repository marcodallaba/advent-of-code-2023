fun main() {

    val input = readInput("Day01")

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
