import java.io.File
import kotlin.math.pow

class Puzzle(registerA: Long, registerB: Long, registerC: Long, commands: List<Int>) {
    var registerA = registerA
        get() = field
    var registerB = registerB
        get() = field
    var registerC = registerC
        get() = field
    var commands = commands
        get() = field

    fun solve(registerA2: Long = registerA): List<Long> {
        var steps = 0
        var registerA = registerA2
        var registerB = this.registerB
        var registerC = this.registerC
        val commands = this.commands

        val out = mutableListOf<Long>()
        var instructionPointer = 0
        while (instructionPointer < commands.size && steps < 5000) {
            steps += 1
            if (steps % 500 == 0) println("${registerA2} - steps: $steps")
            val instruction = commands[instructionPointer]
            val operand = commands[instructionPointer + 1]
            when (instruction) {
                // adv
                0 -> {
//                    println("adv $operand")
                    registerA =
                        (registerA / 2.0.pow(combo(operand, registerA, registerB, registerC).toDouble())).toLong()
                    instructionPointer += 2
                }
                // bxl
                1 -> {
//                    println("bxl $operand")
                    registerB = registerB.xor(operand.toLong())
                    instructionPointer += 2
                }
                // bst
                2 -> {
//                    println("bst $operand")
                    registerB = combo(operand, registerA, registerB, registerC) % 8
                    instructionPointer += 2
                }
                // jnz
                3 -> {
//                    println("jnz")
                    if (registerA != 0L) instructionPointer = operand else instructionPointer += 2
                }
                // bxc
                4 -> {
//                    println("bxc")
                    registerB = registerB.xor(registerC)
                    instructionPointer += 2
                }
                // out
                5 -> {
                    out.addLast(combo(operand, registerA, registerB, registerC) % 8)
                    instructionPointer += 2
                }
                // bdv
                6 -> {
//                    println("bdv $operand")
                    registerB =
                        (registerA / 2.0.pow(combo(operand, registerA, registerB, registerC).toDouble())).toLong()
                    instructionPointer += 2
                }
                // cdv
                7 -> {
//                    println("cdv $operand")
                    registerC =
                        (registerA / 2.0.pow(combo(operand, registerA, registerB, registerC).toDouble())).toLong()
                    instructionPointer += 2
                }

                else -> println("Unknown instruction: ${instruction}")
            }
        }
        return out
    }

    private fun combo(operand: Int, registerA: Long, registerB: Long, registerC: Long): Long {
        if (operand == 4) return registerA
        if (operand == 5) return registerB
        if (operand == 6) return registerC
        if (operand == 7) throw IllegalArgumentException("Combo Operand 7")
        return operand.toLong()
    }

    companion object {
        fun read(fileName: String): Puzzle {
            val lines = File(fileName).readLines()
            val registerA = lines[0].substring("Register A: ".length).toLong()
            val registerB = lines[1].substring("Register B: ".length).toLong()
            val registerC = lines[2].substring("Register C: ".length).toLong()
            val commands = lines[4].substring("Program: ".length).split(",").map { p -> p.toInt() }.toList()
            return Puzzle(registerA, registerB, registerC, commands)
        }
    }
}

