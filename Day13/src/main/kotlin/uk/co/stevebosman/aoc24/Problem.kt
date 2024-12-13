package uk.co.stevebosman.aoc24

import kotlin.math.abs
import kotlin.math.roundToLong

class Problem(private val buttonA: Button, private val buttonB: Button, private val prize: Prize) {

    private fun solve(): Pair<Long, Long>? {
        val aPresses =
            1.0 * (buttonB.dx * prize.positionY - buttonB.dy * prize.positionX) /
                    (buttonA.dy * buttonB.dx - buttonA.dx * buttonB.dy)
        val bPresses =
            1.0 * (buttonA.dy * prize.positionX - buttonA.dx * prize.positionY) /
                    (buttonA.dy * buttonB.dx - buttonA.dx * buttonB.dy)
        if (abs(aPresses - aPresses.roundToLong()) < 0.0000001 && abs(bPresses - bPresses.roundToLong()) < 0.0000001) {
            return Pair(aPresses.roundToLong(), bPresses.roundToLong())
        }
        return null
    }

    fun tokens(): Long {
        val solve = solve()
        if (solve!=null) {
            return 3*solve.first + solve.second
        }
        return 0
    }

    override fun toString(): String {
        return "Problem(buttonA=$buttonA, buttonB=$buttonB, prize=$prize)"
    }
}
