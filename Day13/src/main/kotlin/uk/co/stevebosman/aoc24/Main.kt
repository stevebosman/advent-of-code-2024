package uk.co.stevebosman.aoc24

import java.io.File

fun main(args: Array<String>) {
    val part1 = read(0, args[0])
    println(part1.sumOf { p -> p.tokens() })

    val part2 = read(10000000000000, args[0])
    println(part2.sumOf { p -> p.tokens() })
}

fun read(offset: Long, fileName: String): List<Problem> {
    val lines = File(fileName).readLines()
    return (0..(lines.size-1)/4).map { i -> Problem(Button.of(lines[i*4]), Button.of(lines[i*4+1]),Prize.of(offset, lines[i*4+2]))  }
}
