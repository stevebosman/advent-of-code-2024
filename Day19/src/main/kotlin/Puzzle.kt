package uk.co.stevebosman.aoc24

import java.io.File

class Puzzle(private val patterns: List<String>, private val designs: List<String>) {
    private val designableMemo = mutableListOf<String>()
    private val designMemo = mutableMapOf<String, Long>()

    fun designable(): List<String> {
        return this.designs.filter { d -> isPatternDesignable(d) }.toList()
    }

    private fun isPatternDesignable(design: String): Boolean {
        if (design.isEmpty()) return true
        if (designableMemo.contains(design)) return true
        val result = patterns.any { p -> design.startsWith(p) && isPatternDesignable(design.substring(p.length)) }
        if (result) {
            designableMemo.add(design)
        }
        return result
    }

    fun fullPatterns(): Map<String, Long> {
        val result = mutableMapOf<String, Long>()
        for (d in designable()) {
            println("$d: $designMemo")
            val foundPatterns = fullPatternsForDesign(d)
            result[d] = foundPatterns
        }
        return result
    }

    private fun fullPatternsForDesign(design: String): Long {
        var count = 0L
        if (designMemo.containsKey(design)) {
            return designMemo[design] ?: 0
        }
        patterns.filter { p -> design.startsWith(p) }.forEach { p ->
            if (design == p) {
                count += 1
            } else {
                val subDesign = design.substring(p.length)
                val found = fullPatternsForDesign(subDesign)
                designMemo[subDesign] = found
                count += found
            }
        }
        return count
    }

    companion object {
        fun read(fileName: String): Puzzle {
            val lines = File(fileName).readLines()
            val patterns = lines[0].split(", ").toList()
            return Puzzle(patterns, lines.subList(2, lines.size))
        }
    }
}