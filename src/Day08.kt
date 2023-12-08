fun main() {

    val input = readInput("Day08")

    day08FirstHalf(input)

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
