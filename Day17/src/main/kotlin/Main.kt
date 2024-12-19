import java.time.LocalDateTime

fun main(args: Array<String>) {
    val puzzle = Puzzle.read(args[0])
    val result = puzzle.solve()
    println(result.joinToString(","))

    println(dedicated(puzzle.registerA, result, false).joinToString(","))

    val expected = listOf(2L, 4L, 1L, 3L, 7L, 5L, 1L, 5L, 0L, 3L, 4L, 1L, 5L, 5L, 3L, 0L)

    ("100000".toInt().."777777".toInt())
        .map { i -> "${i.toString(8)}0000000000".toLong(8) }
        .map { i -> Pair(i, dedicated(i, expected, false)) }
        .filter { c -> c.second.size == expected.size }
        .filter { c -> c.second.slice(10..15) == expected.slice(10..15) }
        .map { c -> c.first }
        .forEach { i ->
            part2dedicated(expected, i, i + "10000000000".toLong(8))
        }

    println("invalid solution found at: ${234142032285293.toString(8)}")
}

fun part2dedicated(expected: List<Long>, start: Long, max: Long) {
    var i = start
    var solved = Long.MIN_VALUE;
    var maxFound = 0;
    println("Searching in ${i.toString(8)} -> ${max.toString(8)}")
    while (solved == Long.MIN_VALUE && i < max) {
        i += 1;
        val increment = 100_000_000L
        val countdown = (max - i)
        if (countdown % increment == 0L) println("${LocalDateTime.now()} ${countdown / increment} - best $maxFound")
        val out = dedicated(i, expected, true)
        if (expected == out) {
            solved = i
            println("Solution found $i: $out")
        }
        maxFound = maxOf(maxFound, out.size)
        if (out.size >= 15) {
            println("$i: $out")
        }
    }
}

private fun dedicated(
    i: Long,
    expected: List<Long>,
    shortCircuit: Boolean
): List<Long> {
    var A = i
    val out = mutableListOf<Long>()
    while (A != 0L) {
        var B = (A % 8).xor(3)
        val C = A / (1L shl B.toInt())
        B = B.xor(5).xor(C)
        A /= 8
        val result = B % 8
        if (shortCircuit && out.size < 10 && result != expected[out.size]) break
        out.addLast(result)
    }
    return out
}
