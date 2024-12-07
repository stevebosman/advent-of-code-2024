package uk.co.stevebosman.example

class Solver {
    fun isSolvable(target: Long, values: List<Long>): Boolean {
        val head = values.first()
        val tail = values.drop(1)

        return isSolvable(target, head, tail)
    }

    private fun isSolvable(target: Long, current: Long, values: List<Long>): Boolean {
        println("${target}, ${current}, ${values}")
        if (values.isEmpty()) return current == target
        val head = values.first()
        val tail = values.drop(1)
        return isSolvable(target, current + head, tail) || isSolvable(target, current * head, tail)
    }
}