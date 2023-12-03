import java.util.Scanner
import kotlin.math.max

fun main() {

    val input = readInput("Day03")

    firstHalf03(input)
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

                    println("currentNum: $currentNum, result: $result, right:$right, left:$left, top:$top, bottom:$bottom")

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

                    println("currentNum: $currentNum, result: $result, right:$right, left:$left, top:$top, bottom:$bottom")

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