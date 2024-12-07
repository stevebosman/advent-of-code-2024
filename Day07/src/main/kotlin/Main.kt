package uk.co.stevebosman.example

import java.io.File

fun main(args: Array<String>) {
    val solver = Solver()
    val part1 = read(args[0]).filter { p -> solver.isSolvable(p.first, p.second) }.map { p -> p.first }.sum()
    println("Part 1: ${part1}")
}

fun read(fileName: String): List<Pair<Long, List<Long>>> {
    return File(fileName).readLines().map { s -> convertLine(s)}
}

fun convertLine(line: String): Pair<Long, List<Long>> {
    val split1 = line.split(":")
    return Pair(split1.first().trim().toLong(), split1[1].trim().split(" ").map { s -> s.toLong() })
}

