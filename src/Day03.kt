fun main() {

    val input = readInput("Day03")

    firstHalf03(input)

    secondHalf03(input)
}

fun firstHalf03(input: List<String>) {
    var sum = 0
    input.forEachIndexed { rowIndex, s ->

        var currentNum = 0
        s.forEachIndexed { rightIndex, c ->

            if (c.isDigit()) {
                currentNum = currentNum * 10 + c.digitToInt()
                if (rightIndex == s.length - 1) {

                    val length = currentNum.toString().length
                    val leftIndex = rightIndex - length

                    val right = c.isDigit().not() && c != '.'
                    val left = (leftIndex) >= 0 && s[leftIndex].isDigit().not() && s[leftIndex] != '.'

                    val limitedLeftIndex = if (leftIndex >= 0) {
                        leftIndex
                    } else {
                        0
                    }

                    val limitedRightIndex = if (rightIndex + 1 < s.length) {
                        rightIndex + 1
                    } else {
                        rightIndex
                    }

                    val top = (rowIndex > 0) && input[rowIndex - 1].substring(limitedLeftIndex, limitedRightIndex).any {
                        it.isDigit().not() && it != '.'
                    }

                    val bottom = (rowIndex < input.size - 1) && input[rowIndex + 1].substring(
                        limitedLeftIndex, limitedRightIndex
                    ).any {
                        it.isDigit().not() && it != '.'
                    }

                    val result = right || left || top || bottom

                    if (result) {
                        sum += currentNum
                    }
                    currentNum = 0
                }
            } else {
                if (currentNum > 0) {
                    val length = currentNum.toString().length
                    val leftIndex = rightIndex - length - 1

                    val right = c.isDigit().not() && c != '.'
                    val left = (leftIndex) >= 0 && s[leftIndex].isDigit().not() && s[leftIndex] != '.'

                    val limitedLeftIndex = if (leftIndex >= 0) {
                        leftIndex
                    } else {
                        0
                    }

                    val limitedRightIndex = if (rightIndex + 1 < s.length) {
                        rightIndex + 1
                    } else {
                        rightIndex
                    }

                    val top = (rowIndex > 0) && input[rowIndex - 1].substring(limitedLeftIndex, limitedRightIndex).any {
                        it.isDigit().not() && it != '.'
                    }

                    val bottom = (rowIndex < input.size - 1) && input[rowIndex + 1].substring(
                        limitedLeftIndex, limitedRightIndex
                    ).any {
                        it.isDigit().not() && it != '.'
                    }

                    val result = right || left || top || bottom

                    if (result) {
                        sum += currentNum
                    }
                    currentNum = 0
                }

            }
        }
    }
    println(sum)
}

fun secondHalf03(input: List<String>) {
    val map = HashMap<Pair<Int, Int>, MutableList<Int>>()

    input.forEachIndexed { rowIndex, s ->

        var currentNum = 0
        s.forEachIndexed { rightIndex, c ->

            if (c.isDigit()) {
                currentNum = currentNum * 10 + c.digitToInt()
                if (rightIndex == s.length - 1) {

                    val length = currentNum.toString().length
                    val leftIndex = rightIndex - length

                    if (leftIndex >= 0 && s[leftIndex] == '*') {
                        val pair = Pair(rowIndex, leftIndex)
                        val list = map.getOrDefault(pair, mutableListOf())
                        list.add(currentNum)
                        map[pair] = list
                    }

                    val limitedLeftIndex = if (leftIndex >= 0) {
                        leftIndex
                    } else {
                        0
                    }

                    val limitedRightIndex = if (rightIndex + 1 < s.length) {
                        rightIndex + 1
                    } else {
                        rightIndex
                    }

                    if (rowIndex > 0) {
                        input[rowIndex - 1].substring(limitedLeftIndex, limitedRightIndex).forEachIndexed { index, c ->
                            if (c == '*') {
                                val pair = Pair(rowIndex - 1, limitedLeftIndex + index)

                                val list = map.getOrDefault(pair, mutableListOf())
                                list.add(currentNum)
                                map[pair] = list
                            }
                        }
                    }

                    if (rowIndex < input.size - 1) {
                        input[rowIndex + 1].substring(limitedLeftIndex, limitedRightIndex).forEachIndexed { index, c ->
                            if (c == '*') {
                                val pair = Pair(rowIndex + 1, limitedLeftIndex + index)

                                val list = map.getOrDefault(pair, mutableListOf())
                                list.add(currentNum)
                                map[pair] = list
                            }
                        }
                    }

                    currentNum = 0
                }
            } else {
                if (currentNum > 0) {
                    val length = currentNum.toString().length
                    val leftIndex = rightIndex - length - 1

                    if (c == '*') {
                        val pair = Pair(rowIndex, rightIndex)

                        val list = map.getOrDefault(pair, mutableListOf())
                        list.add(currentNum)
                        map[pair] = list
                    }

                    if (leftIndex >= 0 && s[leftIndex] == '*') {
                        val pair = Pair(rowIndex, leftIndex)

                        val list = map.getOrDefault(pair, mutableListOf())
                        list.add(currentNum)
                        map[pair] = list
                    }

                    val limitedLeftIndex = if (leftIndex >= 0) {
                        leftIndex
                    } else {
                        0
                    }

                    val limitedRightIndex = if (rightIndex + 1 < s.length) {
                        rightIndex + 1
                    } else {
                        rightIndex
                    }

                    if (rowIndex > 0) {
                        input[rowIndex - 1].substring(limitedLeftIndex, limitedRightIndex).forEachIndexed { index, c ->
                            if (c == '*') {
                                val pair = Pair(rowIndex - 1, limitedLeftIndex + index)

                                val list = map.getOrDefault(pair, mutableListOf())
                                list.add(currentNum)
                                map[pair] = list
                            }
                        }
                    }

                    if (rowIndex < input.size - 1) {
                        input[rowIndex + 1].substring(limitedLeftIndex, limitedRightIndex).forEachIndexed { index, c ->
                            if (c == '*') {

                                val pair = Pair(rowIndex + 1, limitedLeftIndex + index)

                                val list = map.getOrDefault(pair, mutableListOf())
                                list.add(currentNum)
                                map[pair] = list
                            }
                        }
                    }

                    currentNum = 0
                }

            }
        }
    }
    var sum = 0
    map.forEach {
        if (it.value.size == 2) {
            sum += (it.value[0] * it.value[1])
        }
    }
    println(sum)
}