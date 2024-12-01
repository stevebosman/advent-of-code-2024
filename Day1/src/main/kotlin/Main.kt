package uk.co.stevebosman.example

import java.io.File
import kotlin.math.absoluteValue

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
    val lists = read(args[0])
    println("Distance: ${distance(lists.first, lists.second)}")
    println("Similarity: ${similarity(lists.first, lists.second)}")
}

fun read(fileName: String): Pair<List<Int>, List<Int>> {
    val lines1 = mutableListOf<Int>()
    val lines2 = mutableListOf<Int>()
    File(fileName).forEachLine {
        val values = it.split(Regex("\\D+"))
        lines1.add(values[0].toInt())
        lines2.add(values[1].toInt())
    }
    return Pair(lines1.sorted(), lines2.sorted())
}

fun distance(list1: List<Int>, list2: List<Int>): Int =
    list1.zip(list2).map { (it.first - it.second).absoluteValue }.sum()

fun similarity(list1: List<Int>, list2: List<Int>): Int =
    list1.map { v1 -> v1 * list2.filter { v2 -> v1 == v2 }.size }.sum()
