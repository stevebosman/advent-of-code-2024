package uk.co.stevebosman.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ButtonTest {
    @Test
    fun create() {
        assertEquals(Button("Button A",94,34), Button.of("Button A: X+94, Y+34"))
    }
}