package uk.co.stevebosman.aoc24

class Prize(val positionX: Long, val positionY: Long) {
    companion object {
        fun of(offset: Long, input: String): Prize {
            val split = input.split(":")
            val positions = split[1].split(",")
            return Prize(offset + positions[0].split("=")[1].toLong(), offset + positions[1].split("=")[1].toLong())
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
        var result = positionX.hashCode()
        result = 31 * result + positionY.hashCode()
        return result
    }

    override fun toString(): String {
        return "Prize(positionX=$positionX, positionY=$positionY)"
    }

}