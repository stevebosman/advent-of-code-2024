package uk.co.stevebosman.aoc24

fun main(args: Array<String>) {
    val puzzle = Puzzle.read(args[0])
    val designable = puzzle.designable()
    println("${designable.size}: $designable")
}


