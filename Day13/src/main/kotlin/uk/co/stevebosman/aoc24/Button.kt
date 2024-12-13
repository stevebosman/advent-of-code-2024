package uk.co.stevebosman.aoc24

class Button(val name: String, val dx: Int, val dy: Int) {
    companion object {
        fun of(input: String): Button {
            val split = input.split(":")
            val name = split[0]
            val deltas = split[1].split(",")
            return Button(name, deltas[0].split("+")[1].toInt(), deltas[1].split("+")[1].toInt())
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Button

        if (dx != other.dx) return false
        if (dy != other.dy) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dx
        result = 31 * result + dy
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "Button(name='$name', dx=$dx, dy=$dy)"
    }
}