import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class MainKtTest {
    @Test
    fun testExample() {
        assertDoesNotThrow { main(arrayOf("example1.txt")) }
    }

    @Test
    fun testInput() {
        assertDoesNotThrow { main(arrayOf("input.txt")) }
    }
}