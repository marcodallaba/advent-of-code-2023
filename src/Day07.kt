fun main() {

    val input = readInput("Day07")

    day07(input)

}

enum class Type {
    FiveOfAKind, FourOfAKind, FullHouse, ThreeOfAKind, TwoPair, OnePair, HighCard
}

enum class Card(val rawValue: Char) {
    A('A'),
    K('K'),
    Q('Q'),
    T('T'),
    Nine('9'),
    Eight('8'),
    Seven('7'),
    Six('6'),
    Five('5'),
    Four('4'),
    Three('3'),
    Two('2'),
    J('J'),
}

fun day07(input: List<String>) {
    var inputRemapped = input.map {
        val split = it.split(" ")
        Pair(split[0], split[1].toInt())
    }

    val myCustomComparator = Comparator<Pair<String, Int>> { a, b ->
        when {
            (a == null && b == null) -> 0
            (a == null) -> -1
            (b == null) -> 1
            else -> {
                val typeA = getType(a)
                val typeB = getType(b)
                if (typeA == typeB) {
                    var index = 0
                    while (index < 5) {
                        val cardA = Card.entries.first {
                            it.rawValue == a.first[index]
                        }
                        val cardB = Card.entries.first {
                            it.rawValue == b.first[index]
                        }
                        if (cardA == cardB) {
                            index++
                        } else {
                            return@Comparator cardA.compareTo(cardB)
                        }
                    }
                    0
                } else {
                    typeA.compareTo(typeB)
                }
            }
        }
    }

    inputRemapped = inputRemapped.sortedWith(myCustomComparator)

    inputRemapped.println()

    var sum = 0L
    var maxRank = inputRemapped.size
    inputRemapped.forEach {
        sum += (it.second * maxRank)
        maxRank--
    }
    sum.println()
}

private fun getType(a: Pair<String, Int>): Type {
    val groupByA = a.first.groupBy {
        it
    }.map {
        Pair(it.key, it.value.size)
    }.sortedByDescending { it.second }.toMutableList()


    val jValue = groupByA.firstOrNull {
        it.first == 'J'
    }

    if (jValue != null && jValue.second < 5) {
        groupByA.removeIf {
            it.first == 'J'
        }
        val second = groupByA.first()
        groupByA.removeAt(0)
        val newSecond = second.copy(first = second.first, second = second.second + jValue.second)
        groupByA.add(0, newSecond)
    }

    val firstValue = groupByA[0]

    val typeA: Type = if (firstValue.second == 5) {
        Type.FiveOfAKind
    } else if (firstValue.second == 4) {
        Type.FourOfAKind
    } else if (firstValue.second == 3) {
        val secondValue = groupByA[1]
        if (secondValue.second == 2) {
            Type.FullHouse
        } else {
            Type.ThreeOfAKind
        }
    } else if (firstValue.second == 2) {
        val secondValue = groupByA[1]
        if (secondValue.second == 2) {
            Type.TwoPair
        } else {
            Type.OnePair
        }
    } else {
        Type.HighCard
    }
    return typeA
}