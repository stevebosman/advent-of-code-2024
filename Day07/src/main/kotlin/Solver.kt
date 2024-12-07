package uk.co.stevebosman.example

import kotlin.math.pow

class Solver(val part2: Boolean) {
    fun isSolvable(target: Long, values: List<Long>): Boolean {
//        println("${target}")
        val head = values.first()
        val tail = values.drop(1)

        return isSolvable(target, head, tail)
    }

    private fun isSolvable(target: Long, current: Long, values: List<Long>): Boolean {
        if (values.isEmpty()) return current == target
        val head = values.first()
        val tail = values.drop(1)
        return isSolvable(target, current + head, tail)
                || isSolvable(target, current * head, tail)
                || (part2 && isSolvable(target, concat(current, head), tail))
    }

    private val concats: MutableMap<Pair<Long, Long>, Long> = mutableMapOf();

    private fun concat(n1: Long, n2: Long): Long {
        val key = Pair(n1, n2)
        return concats.get(key)?:forceConcat(key)
    }

    private fun forceConcat(p: Pair<Long,Long>): Long {
        val concat = p.first * 10.0.pow(1 + Math.log10(p.second.toDouble()).toInt()).toInt() + p.second
        concats.put(p, concat)
        return concat
    }
}