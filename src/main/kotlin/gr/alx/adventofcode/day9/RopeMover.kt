package gr.alx.adventofcode.day9

class RopeMover {
    fun move(move: String): RopePosition {
        val ropePosition = RopePosition()
        val (dx, dy) = parseMove(move)
        return ropePosition.move(dx, dy)
    }

    private fun parseMove(move: String): Position {
        val (direction, distance) = move.split(" ")
        return when (direction) {
            "R" -> Position(x = distance.toInt(), y = 0)
            "L" -> Position(x = -distance.toInt(), y = 0)
            "D" -> Position(x = 0, y = -distance.toInt())
            "U" -> Position(x = 0, y = distance.toInt())
            else -> throw IllegalStateException()
        }

    }

    fun move(move: List<String>): RopePosition {
        val ropePosition = RopePosition()
        move.forEach {
            val (dx, dy) = parseMove(it)

            ropePosition.move(dx, dy)
        }
        return ropePosition
    }

    fun moveAll(move: List<String>): RopePosition2 {
        val ropePosition = RopePosition2()
        move.forEach {
            val (dx, dy) = parseMove(it)

            ropePosition.moveKnots(dx, dy)
        }
        return ropePosition
    }

}
