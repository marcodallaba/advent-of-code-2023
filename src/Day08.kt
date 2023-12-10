fun main() {

    val input = readInput("Day08")

    //day08FirstHalf(input)
    day08SecondHalf(input)

}

fun day08FirstHalf(input: List<String>) {

    val instructions = input[0]

    val map: Map<String, Triple<String, String, String>> = input.subList(2, input.size).associate {
        val position = it.substring(0, 3)
        val left = it.substring(7, 10)
        val right = it.substring(12, 15)

        position to Triple(position, left, right)
    }
    map.println()

    var currentPosition = map["AAA"]!!

    var instructionIndex = 0
    var step = 0
    while (currentPosition.first != "ZZZ") {
        step++
        currentPosition = if (instructions[instructionIndex] == 'R') {
            map[currentPosition.third]!!
        } else {
            map[currentPosition.second]!!
        }
        instructionIndex = (instructionIndex + 1) % instructions.length
    }
    step.println()
}

fun day08SecondHalf(input: List<String>) {
    val instructions = input[0]

    val map: Map<String, Triple<String, String, String>> = input.subList(2, input.size).associate {
        val position = it.substring(0, 3)
        val left = it.substring(7, 10)
        val right = it.substring(12, 15)

        position to Triple(position, left, right)
    }

    val counts = map.keys.filter { it.endsWith("A") }.map { start ->
        var current = start
        var count = 0L
        while (!current.endsWith("Z")) {
            instructions.forEach {
                current = if (it == 'R') {
                    map[current]!!.third
                } else {
                    map[current]!!.second
                }
            }
            count += instructions.length
        }
        count
    }
    val ans = counts.reduce { acc, i -> findLCM(acc, i) }
    ans.println()
}

fun findLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}