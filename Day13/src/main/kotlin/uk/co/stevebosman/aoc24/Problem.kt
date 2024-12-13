package uk.co.stevebosman.aoc24

import kotlin.math.abs
import kotlin.math.roundToInt

class Problem(val buttonA: Button, val buttonB: Button, val prize: Prize) {

    fun solve(): Pair<Int, Int>? {
        val aPresses =
            1.0 * (buttonB.dx * prize.positionY - buttonB.dy * prize.positionX) /
                    (buttonA.dy * buttonB.dx - buttonA.dx * buttonB.dy)
        val bPresses =
            1.0 * (buttonA.dy * prize.positionX - buttonA.dx * prize.positionY) /
                    (buttonA.dy * buttonB.dx - buttonA.dx * buttonB.dy)
        if (abs(aPresses - aPresses.roundToInt()) < 0.0000001 && abs(bPresses - bPresses.roundToInt()) < 0.0000001) {
            return Pair(aPresses.roundToInt(), bPresses.roundToInt())
        }
        return null
    }

    fun tokens(): Int {
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
