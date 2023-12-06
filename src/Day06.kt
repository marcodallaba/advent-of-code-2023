import java.util.*

fun main() {

    val input = readInput("Day06")

    //firstHalf06(input)

    secondHalf06(input)

}

fun firstHalf06(input: List<String>) {

    val time = input[0].split(':')[1].trim().split("\\s+".toRegex()).map {
        it.toInt()
    }
    val distance = input[1].split(':')[1].trim().split("\\s+".toRegex()).map {
        it.toInt()
    }

    var ways = 1
    time.forEachIndexed { index, it ->

        var count = 0
        for (i in 0..it) {
            if ((i * (it - i)) > distance[index]) {
                count++
            }
        }
        ways *= count
    }
    ways.println()

}

fun secondHalf06(input: List<String>) {
    val time = input[0].split(':')[1].trim().split("\\s+".toRegex()).reduce { acc, s ->
        acc + s
    }.toLong()

    val distance = input[1].split(':')[1].trim().split("\\s+".toRegex()).reduce { acc, s ->
        acc + s
    }.toLong()

    var count = 0
    for (i in 0..time) {
        if ((i * (time - i)) > distance) {
            count++
        }
    }

    count.println()
}
