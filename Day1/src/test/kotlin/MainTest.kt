import uk.co.stevebosman.example.distance
import uk.co.stevebosman.example.main
import uk.co.stevebosman.example.read
import uk.co.stevebosman.example.similarity
import kotlin.test.Test
import kotlin.test.assertEquals

class MainTest {
    @Test
    fun testMain() {
        main(arrayOf("src/test/resources/example.txt"))
    }

    @Test
    fun testSingle() {
        val actual = read("src/test/resources/1line.txt")
        assertEquals(listOf(1), actual.first)
        assertEquals(listOf(2), actual.second)
        assertEquals(1, distance(actual.first, actual.second))
        assertEquals(0, similarity(actual.first, actual.second))
    }

    @Test
    fun testDouble() {
        val actual = read("src/test/resources/2lines.txt")
        assertEquals(listOf(1, 3), actual.first)
        assertEquals(listOf(1, 2), actual.second)
        assertEquals(1, distance(actual.first, actual.second))
        assertEquals(1, similarity(actual.first, actual.second))
    }

    @Test
    fun testBigger() {
        val actual = read("src/test/resources/5lines.txt")
        assertEquals(listOf(1, 3, 5, 7, 9), actual.first)
        assertEquals(listOf(1, 2, 2, 3, 4), actual.second)
        assertEquals(13, distance(actual.first, actual.second))
        assertEquals(4, similarity(actual.first, actual.second))
    }

    @Test
    fun testExample() {
        val actual = read("src/test/resources/example.txt")
        assertEquals(listOf(1, 2, 3, 3, 3, 4), actual.first)
        assertEquals(listOf(3, 3, 3, 4, 5, 9), actual.second)
        assertEquals(11, distance(actual.first, actual.second))
        assertEquals(31, similarity(actual.first, actual.second))
    }
}