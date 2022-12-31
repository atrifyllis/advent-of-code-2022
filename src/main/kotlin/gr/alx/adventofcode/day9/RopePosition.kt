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
                moveHead(inc, 0)
                handleTail(inc, 0)
            }
        } else if (dy != 0) {
            val inc = if (dy > 0) 1 else -1
            for (i in 1..abs(dy)) {
                moveHead(0, inc)
                handleTail(0, inc)
            }
        }
        return this
    }

    private fun handleTail(x: Int, y: Int) {
        if (!tail.isAdjacent(head)) {
            moveTail(x, y)
            // handles diagonal adjacency where tail must move in both directions
            if (tail.x != head.x && tail.y != head.y) {
                if (x != 0) {
                    moveTail(0, head.y - tail.y)
                }
                if (y != 0) {
                    moveTail(head.x - tail.x, 0)
                }
            }
            visited.add(tail)
        }
    }

    private fun moveHead(x: Int, y: Int) {
        head = Position(head.x + x, head.y + y)
    }

    private fun moveTail(x: Int, y: Int) {
        tail = Position(tail.x + x, tail.y + y)
    }
}

data class Position(var x: Int = 0, var y: Int = 0) {
    fun isAdjacent(other: Position): Boolean {
        return abs(this.x - other.x) <= 1
                && abs(this.y - other.y) <= 1
    }
}
