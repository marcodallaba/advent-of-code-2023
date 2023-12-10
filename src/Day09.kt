fun main() {

    val input = readInput("Day09")

    day09SecondHalf(input)

}

fun day09SecondHalf(input: List<String>) {

    var sum = 0
    input.forEach {
        val intList = it.split("\\s+".toRegex()).map { it.toInt() }
        val calcDiff2 = calcDiff2(intList)
        sum+= calcDiff2
    }
    sum.println()
}
fun day09FirstHalf(input: List<String>) {

    var sum = 0
    input.forEach {
        val intList = it.split("\\s+".toRegex()).map { it.toInt() }
        sum+= calcDiff1(intList)
    }
    sum.println()
}

fun calcDiff1(list: List<Int>): Int {

    var prev = list[0]
    val newList = mutableListOf<Int>()
    for (i in 1 until list.size) {
        val newValue = list[i] - prev
        prev = list[i]
        newList.add(newValue)
    }
    return if (newList.any { it != 0 }) {
        list.last() + calcDiff1(newList)
    } else {
        list.last() + 0
    }
}

fun calcDiff2(list: List<Int>): Int {

    var prev = list[0]
    val newList = mutableListOf<Int>()
    for (i in 1 until list.size) {
        val newValue = list[i] - prev
        prev = list[i]
        newList.add(newValue)
    }
    return if (newList.any { it != 0 }) {
        list.first() - calcDiff2(newList)
    } else {
        list.first() - 0
    }
}