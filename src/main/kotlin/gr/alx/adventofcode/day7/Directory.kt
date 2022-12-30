package gr.alx.adventofcode.day7

data class Directory(
    val name: String,
    val parent: Directory?,
) {
    var directories: MutableList<Directory> = mutableListOf()
    var files: MutableList<File> = mutableListOf()

    fun totalSize(): Long {
        return directories.sumOf { it.totalSize() } + files.sumOf { it.size }
    }
}
