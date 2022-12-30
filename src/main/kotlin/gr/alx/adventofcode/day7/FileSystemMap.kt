package gr.alx.adventofcode.day7

class FileSystemMap(var rootDirectory: Directory = Directory("/", null)) {


    lateinit var currentDirectory: Directory

    fun sumOfDirsWithMaxSize(maxSize: Long): Long {
        return getAllDirsFlat(this.rootDirectory.directories, emptyList())
            .filter { it.totalSize() <= maxSize }
            .onEach { println("${it.name} - ${it.totalSize()}") }
            .sumOf { it.totalSize() }
    }


    fun chooseDirToDelete(targetFreeSpaceSize: Long): Directory {
        val currentFreeSpace = 70000000 - this.rootDirectory.totalSize()
        return getAllDirsFlat(this.rootDirectory.directories, emptyList())
            .filter { it.totalSize() + currentFreeSpace >= targetFreeSpaceSize }
            .sortedBy { it.totalSize() }
            .first()
    }

    private tailrec fun getAllDirsFlat(remaining: List<Directory>, collected: List<Directory>): List<Directory> {
        if (remaining.isEmpty()) return collected
        val head = remaining.first()
        val tail = remaining.drop(1)
        return getAllDirsFlat(head.directories.plus(tail), collected.plus(head))

    }

}
