import java.lang.Exception
import javax.swing.plaf.OptionPaneUI

enum class MatchResult { WIN, DRAW, LOSE }
enum class Move { ROCK, PAPER, SCISSORS }

fun main() {
    fun calcOwnMovePoints(move: Move): Int {
        return when (move) {
            Move.ROCK -> 1
            Move.PAPER -> 2
            Move.SCISSORS -> 3
        }
    }

    fun ownMoveFactory(ownMoveString: String): Move? {
        return when (ownMoveString) {
            "X" -> Move.ROCK
            "Y" -> Move.PAPER
            "Z" -> Move.SCISSORS
            else -> null
        }
    }

    fun opponentMoveFactory(opponentMoveString: String): Move? {
        return when (opponentMoveString) {
            "A" -> Move.ROCK
            "B" -> Move.PAPER
            "C" -> Move.SCISSORS
            else -> null
        }
    }

    fun calculateResult(ownMove: Move, opponentMove: Move): MatchResult {
        // A bit verbose - but pretty easy to read
        return when (ownMove) {
            Move.ROCK -> when(opponentMove) {
                Move.ROCK -> MatchResult.DRAW
                Move.PAPER -> MatchResult.LOSE
                Move.SCISSORS -> MatchResult.WIN
            }
            Move.PAPER -> when(opponentMove) {
                Move.ROCK -> MatchResult.WIN
                Move.PAPER -> MatchResult.DRAW
                Move.SCISSORS -> MatchResult.LOSE
            }
            Move.SCISSORS -> when(opponentMove) {
                Move.ROCK -> MatchResult.LOSE
                Move.PAPER -> MatchResult.WIN
                Move.SCISSORS -> MatchResult.DRAW
            }
        }
    }

    fun matchResultPoints(result: MatchResult): Int {
        return when(result) {
            MatchResult.WIN -> 6
            MatchResult.DRAW -> 3
            MatchResult.LOSE -> 0
        }
    }

    fun part1(input: List<String>): Int {
        val points = input.map { it.split(" ") }.sumOf {
            val opponentMove = opponentMoveFactory(it[0])
            val ownMove = ownMoveFactory(it[1])

            val ownMovePoints = calcOwnMovePoints(ownMove!!)

            val matchResult = calculateResult(ownMove, opponentMove!!)
            val matchResultPoints = matchResultPoints(matchResult)

            matchResultPoints + ownMovePoints
        }
        return points
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
