fun main() {
    // TODO: Make prioritization function
    fun priority(s: Char): Int {
        return when(s.isLowerCase()) {
            true -> s.code - "a".toCharArray().first().code
            false -> s.code - "A".toCharArray().first().code + 26
        } + 1
    }

    fun compartmentalize(s: String): List<String> {
        // Always even number of items
        return s.chunked(s.length / 2)
    }

    fun findCommon(compartments: List<String>): String? {
        val compartmentSets = compartments.map { it.toSet() }

        return compartmentSets.reduce { acc, item -> acc.intersect(item) }.toList().first().toString()
    }

    fun part1(input: List<String>): Int {
        val sacksOfCompartments = input.map { compartmentalize(it) }
        val commonLettersPerSack = sacksOfCompartments.map { compartments -> findCommon(compartments) }
        val priorities = commonLettersPerSack.map { priority(it!!.first()) }

        return priorities.sum()
    }

    fun part2(input: List<String>): Int {
        val groups = input.chunked(3)
        val commonLettersPerGroup = groups.map { findCommon(it) }
        val priorities = commonLettersPerGroup.map { priority(it!!.first()) }

        return priorities.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
