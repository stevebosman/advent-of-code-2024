package uk.co.stevebosman.aoc24

class Prize(val positionX: Int, val positionY: Int) {
    companion object {
        fun of(input: String): Prize {
            val split = input.split(":")
            val positions = split[1].split(",")
            return Prize(positions[0].split("=")[1].toInt(), positions[1].split("=")[1].toInt())
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Prize

        if (positionX != other.positionX) return false
        if (positionY != other.positionY) return false

        return true
    }

    override fun hashCode(): Int {
        var result = positionX
        result = 31 * result + positionY
        return result
    }

    override fun toString(): String {
        return "Prize(positionX=$positionX, positionY=$positionY)"
    }

}