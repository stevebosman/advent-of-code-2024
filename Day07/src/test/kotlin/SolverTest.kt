import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import uk.co.stevebosman.example.Solver

class SolverTest {

    @ParameterizedTest
    @CsvSource(
        "190, 10 19, true",
        "3267, 81 40 27, true",
        "83, 17 5, false",
        "156, 15 6, false",
        "7290, 6 8 6 15, false",
        "161011, 16 10 13, false",
        "192, 17 8 14, false",
        "21037, 9 7 18 13, false",
        "292, 11 6 16 20, true"
    )
    fun isSolvable(target: Long, values: String, expected: Boolean) {
        assertEquals(expected, Solver().isSolvable(target, values.split(" ").map { s -> s.toLong() }))
    }

}