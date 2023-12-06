fun main() {

    val input = readInput("Day05")

    firstHalf05(input)

    secondHalf05(input)

}

fun firstHalf05(input: List<String>) {
    SeedFarmer(input).minimumLocation().println()
}

fun secondHalf05(input: List<String>) {
    SeedFarmer(input).minimumLocationWithSeedRanges().println()
}
