package uk.co.stevebosman.example

import jdk.internal.org.jline.keymap.KeyMap.key
import java.io.File


fun main(args: Array<String>) {
    val sum = read(args[0])
    println("Sum: ${sum}")
}

fun read(fileName: String): Int {
    val rules = mutableMapOf<Int, MutableSet<Int>>()
    var result = 0
    var readRules = true;
    File(fileName).forEachLine {
        if (it.isBlank()) {
            readRules = false;
        } else if (readRules) {
            val rule = it.split("|").map { it.toInt() }
            rules.computeIfAbsent(rule[0]) { k -> mutableSetOf() }.add(rule[1])
        } else {
            val pages = it.split(",").map { it.toInt() }
            if (pages.windowed(2)
                     .map { a -> rules[a[0]]?.contains(a[1]) == true }
                     .reduce {acc, r -> acc && r }) {
                result += pages[pages.size/2]
            }
        }
    }
    return result
}
