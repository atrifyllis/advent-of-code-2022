package gr.alx.adventofcode.day9

import kotlin.math.abs

data class RopePosition2(
    var knots: MutableList<Position> = (1..10).map { Position() }.toMutableList(),
    var visited: MutableSet<Position> = mutableSetOf(Position()),
) {

    fun moveKnots(dx: Int, dy: Int): RopePosition2 {
        if (dx != 0) {
            val inc = if (dx > 0) 1 else -1
            for (i in 1..abs(dx)) {
                knots[0] = moveKnot(inc, 0, knots[0])
                for (i in 0..8) {
                    knots[i + 1] = handleTrailingKnots(inc, 0, knots[i], knots[i + 1], i)
                }
            }
        } else if (dy != 0) {
            val inc = if (dy > 0) 1 else -1
            for (i in 1..abs(dy)) {
                knots[0] = moveKnot(0, inc, knots[0])
                for (i in 0..8) {
                    knots[i + 1] = handleTrailingKnots(0, inc, knots[i], knots[i + 1], i)
                }
            }
        }
        return this
    }


    private fun handleTrailingKnots(x: Int, y: Int, head: Position, tail: Position, i: Int): Position {
        var newTail = tail
        if (!newTail.isAdjacent(head)) {
            newTail = moveKnot(x, y, newTail)
            // handles diagonal adjacency where tail must move in both directions
            if (newTail.x != head.x && newTail.y != head.y || abs(newTail.x - head.x) > 1 || abs(newTail.y - head.y) > 1) {
                if (x != 0) {
                    newTail = moveKnot(0, head.y - newTail.y, newTail)
                }
                if (y != 0) {
                    newTail = moveKnot(head.x - newTail.x, 0, newTail)
                }
            }
            println("i+1: ${i + 1} -- knots.size - 1: ${knots.size - 1}")
            if (i + 1 == knots.size - 1) {
                visited.add(newTail)
            }
        }
        return newTail
    }

    private fun moveKnot(x: Int, y: Int, knot: Position): Position {
        return Position(knot.x + x, knot.y + y)
    }
}


