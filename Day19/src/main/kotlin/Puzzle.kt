package uk.co.stevebosman.aoc24

import java.io.File

class Puzzle(val patterns: List<String>, val designs: List<String>) {

    fun designable(): List<String> {
        val memo = mutableListOf<String>()
        return this.designs.filter { d -> isDesignable(d, memo) }.toList()
    }

    private fun isDesignable(design: String, memo: MutableList<String>): Boolean {
        if (design.isEmpty()) return true
        if (memo.contains(design)) return true
        val result = patterns.any { p -> design.startsWith(p) && isDesignable(design.substring(p.length), memo) }
        if (result) {
            memo.add(design)
        }
//        println("$design is designable? $result")
        return result
    }


    companion object {
        fun read(fileName: String): Puzzle {
            val lines = File(fileName).readLines()
            val patterns = lines[0].split(", ").toList()
            return Puzzle(patterns, lines.subList(2, lines.size))
        }
    }
}