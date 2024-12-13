package uk.co.stevebosman.aoc24

import java.io.File

fun main(args: Array<String>) {
    val problems = read(args[0])
    problems.forEach { p -> println(p.solve()) }
    println(problems.sumOf { p -> p.tokens() })
}

fun read(fileName: String): List<Problem> {
    val lines = File(fileName).readLines()
    return (0..(lines.size-1)/4).map { i -> Problem(Button.of(lines[i*4]), Button.of(lines[i*4+1]),Prize.of(lines[i*4+2]))  }
}

fun convertLine(line: String): Pair<Long, List<Long>> {
    val split1 = line.split(":")
    return Pair(split1.first().trim().toLong(), split1[1].trim().split(" ").map { s -> s.toLong() })
}
