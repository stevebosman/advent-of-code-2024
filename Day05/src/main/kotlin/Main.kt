package uk.co.stevebosman.example

import java.io.File


fun main(args: Array<String>) {
    val sum = read(args[0])
    println("Sum (Correct, Reordered): ${sum}")
}

fun read(fileName: String): Pair<Int, Int> {
    val rules = mutableMapOf<Int, MutableSet<Int>>()
    var sumCorrect = 0
    var sumReordered = 0
    var readRules = true;
    File(fileName).forEachLine {
        if (it.isBlank()) {
            readRules = false;
        } else if (readRules) {
            val rule = it.split("|").map { it.toInt() }
            rules.computeIfAbsent(rule[0]) { k -> mutableSetOf() }.add(rule[1])
        } else {
            val pages = it.split(",").map { it.toInt() }
            if (isValidUpdate(pages, rules)) {
                sumCorrect += pages[pages.size / 2]
            } else {
                val chain = findChain(pages, pages, rules)
                if (chain != null) {
                    sumReordered += chain[chain.size / 2]
                }
            }
        }
    }
    return Pair(sumCorrect, sumReordered)
}

private fun isValidUpdate(
    pages: List<Int>,
    rules: MutableMap<Int, MutableSet<Int>>
) = pages.windowed(2)
    .map { a -> rules[a[0]]?.contains(a[1]) == true }
    .reduce { acc, r -> acc && r }

private fun findChain(
    potentials: List<Int>,
    pages: List<Int>,
    rules: MutableMap<Int, MutableSet<Int>>
): List<Int>? {
    if (pages.size == 1) return pages
    for (page in potentials) {
        val potential = rules[page]?.filter { pages.contains(it) }
        if (potential != null && pages.find { potential.contains(it) } != null) {
            val subChain = findChain(potential, pages.filter { it != page }, rules)
            if (subChain != null) {
                return listOf(page) + subChain
            }
        }
    }
    return null
}