import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PuzzleTest {
    @Test
    fun readExamplePuzzle() {
        val actual = Puzzle.read("example1.txt")
        assertEquals(729, actual.registerA)
        assertEquals(0, actual.registerB)
        assertEquals(0, actual.registerC)
        assertEquals(listOf(0, 1, 5, 4, 3, 0), actual.commands)
    }

    @Test
    fun solveExamplePuzzle() {
        val actual = Puzzle.read("example1.txt").solve()
        assertEquals(listOf(4, 6, 3, 5, 6, 3, 5, 2, 1, 0), actual)
    }
}