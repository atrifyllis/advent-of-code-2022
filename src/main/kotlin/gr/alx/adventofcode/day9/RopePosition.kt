package gr.alx.adventofcode.day9

import kotlin.math.abs

data class RopePosition(
    var head: Position = Position(),
    var tail: Position = Position(),
    var visited: MutableSet<Position> = mutableSetOf(Position()),
) {
    fun move(dx: Int, dy: Int): RopePosition {
        if (dx != 0) {
            val inc = if (dx > 0) 1 else -1
            for (i in 1..abs(dx)) {
                head = moveKnot(inc, 0, head)
                tail = handleTrailingKnot(inc, 0, head, tail)
            }
        } else if (dy != 0) {
            val inc = if (dy > 0) 1 else -1
            for (i in 1..abs(dy)) {
                head = moveKnot(0, inc, head)
                tail = handleTrailingKnot(0, inc, head, tail)
            }
        }
        return this
    }

    private fun handleTrailingKnot(x: Int, y: Int, head: Position, tail: Position): Position {
        var newTail = tail
        if (!newTail.isAdjacent(head)) {
            newTail = moveKnot(x, y, newTail)
            // handles diagonal adjacency where tail must move in both directions
            if (newTail.x != head.x && newTail.y != head.y) {
                if (x != 0) {
                    newTail = moveKnot(0, head.y - newTail.y, newTail)
                }
                if (y != 0) {
                    newTail = moveKnot(head.x - newTail.x, 0, newTail)
                }
            }
            visited.add(newTail)
        }
        return newTail
    }

    private fun moveKnot(x: Int, y: Int, pos: Position): Position {
        return Position(pos.x + x, pos.y + y)
    }
}

data class Position(var x: Int = 0, var y: Int = 0) {
    fun isAdjacent(other: Position): Boolean {
        return abs(this.x - other.x) <= 1
                && abs(this.y - other.y) <= 1
    }
}
