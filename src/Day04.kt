typealias Range = Pair<Int, Int>

fun main() {
    fun parseLine(line: String): List<Range> {
        // Split on comma
        val ranges = line.split(",").map {
            val (first, second) = it.split("-").take(2)
            Range(first.toInt(), second.toInt())
        }
        return ranges
    }

    fun contains(a: Range, b: Range): Boolean {
        return if (a.first < b.first) {
            a.second >= b.second
        } else if (a.first == b.first) {
            true
        } else {
            // b.first < a.first
            b.second >= a.second
        }
    }

    fun part1(input: List<String>): Int {
        val ranges = input.map { parseLine(it) }
        val isRangesOverlapping = ranges.map {
            val (a, b) = it.take(2)
            contains(a, b)
        }

        return isRangesOverlapping.count { it }
    }

    fun nonContainingOverlap(a: Range, b: Range): Boolean {
        return ((b.first <= a.first) && (a.first <= b.second))
    }

    fun overlapAtAll(a: Range, b: Range): Boolean {
        return contains(a, b) || nonContainingOverlap(a, b) || nonContainingOverlap(b, a)
    }

    fun part2(input: List<String>): Int {
        val ranges = input.map { parseLine(it) }
        val isOverlappingAtAll = ranges.map {
            val (a, b) = it.take(2)
            overlapAtAll(a, b)
        }
        return isOverlappingAtAll.count { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)

    val input = readInput("Day04")
    println(part1(input))

    check(part2(testInput) == 4)
    println(part2(input))
}
