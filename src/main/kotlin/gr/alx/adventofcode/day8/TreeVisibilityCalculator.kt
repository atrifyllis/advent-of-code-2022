package gr.alx.adventofcode.day8

class TreeVisibilityCalculator {
    fun parseGrid(lines: List<String>): Array<IntArray> {
        val treeGrid: Array<IntArray> = Array(lines[0].length) { IntArray(lines.size) }
        lines
            .forEachIndexed { lineIndex, line ->
                line
                    .forEachIndexed { columnIndex, column ->
                        treeGrid[lineIndex][columnIndex] = column.toString().toInt()
                    }
            }
        return treeGrid
    }

    fun countVisibleTrees(lines: List<String>): Int {
        var count = 0
        val grid = parseGrid(lines)

        grid
            .forEachIndexed { lineIndex, columns ->
                columns
                    .forEachIndexed { columnIndex, tree ->
                        if (isVisible(grid, tree, lineIndex, columnIndex)) {
                            count++
                        }
                    }
            }
        return count
    }

    private fun isVisible(grid: Array<IntArray>, tree: Int, lineIndex: Int, columnIndex: Int): Boolean {
        val line: IntArray = grid[lineIndex]
        val column: List<Int> = grid.map { it[columnIndex] }

        val lineSize = line.size
        val columnSize = column.size

        if (isEdgeTree(lineIndex, lineSize, columnIndex, columnSize)) {
            return true
        }

        val isVisibleInLine = checkIfTreeVisibleInLine(line, columnIndex, lineSize, tree)

        val isVisibleInColumn = checkIfTreeVisibleInColumn(column, lineIndex, columnSize, tree)

        return isVisibleInLine || isVisibleInColumn
    }

    private fun checkIfTreeVisibleInLine(line: IntArray, columnIndex: Int, lineSize: Int, tree: Int): Boolean {
        val leftPart = line.toList().subList(0, columnIndex)
        val rightPart = line.toList().subList(columnIndex + 1, lineSize)
        return leftPart.none { it >= tree } || rightPart.none { it >= tree }
    }

    private fun checkIfTreeVisibleInColumn(column: List<Int>, lineIndex: Int, columnSize: Int, tree: Int): Boolean {
        val topPart = column.subList(0, lineIndex)
        val bottomPart = column.subList(lineIndex + 1, columnSize)
        return topPart.none { it >= tree } || bottomPart.none { it >= tree }
    }

    private fun isEdgeTree(lineIndex: Int, lineSize: Int, columnIndex: Int, columnSize: Int) =
        lineIndex == 0 || lineIndex == lineSize - 1 || columnIndex == 0 || columnIndex == columnSize - 1

}
