fun main() {
    fun splitOnBlank(list: List<String>): List<List<Int>> {
        return list.fold(mutableListOf(mutableListOf<Int>())) { acc, item ->
            if (item.isBlank()) {
                acc.add(mutableListOf())
            } else {
                acc.last().add(item.toInt())
            }
            acc
        }
    }

    fun part1(input: List<String>): Int {
        val groups = splitOnBlank(input)
        val sums = groups.map { it.sum() }

        println(input)
        println(groups)
        println(sums)

        return sums.max()
    }

    fun part2(input: List<String>): Int {
        val groups = splitOnBlank(input)

        return groups.map { it.sum() }.sorted().takeLast(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
