package uk.co.stevebosman.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PrizeTest {
    @Test
    fun create() {
        assertEquals(Prize(8400, 5400), Prize.of(0, "Prize: X=8400, Y=5400"))
    }
}