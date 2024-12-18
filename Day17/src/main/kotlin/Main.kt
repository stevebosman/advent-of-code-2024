fun main(args: Array<String>) {
    val puzzle = Puzzle.read(args[0])
    println(puzzle.solve().joinToString(","))
}

