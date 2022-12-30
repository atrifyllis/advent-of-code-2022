package gr.alx.adventofcode.day7

class FileSystemMapper {
    fun map(input: List<String>): FileSystemMap {
        val fileSystemMap = FileSystemMap()
        input.forEachIndexed { index, it ->
            when {
                it.startsWith("$") -> parseCommand(it, fileSystemMap, input, index)
            }
        }
        return fileSystemMap
    }


    private fun parseCommand(command: String, fileSystemMap: FileSystemMap, lines: List<String>, index: Int) {

        when {
            command.startsWith("$ cd /") -> fileSystemMap.currentDirectory = fileSystemMap.rootDirectory
            command == "$ ls" -> parseLsLines(extractLsLines(index + 1, lines), fileSystemMap)
            command.startsWith("$ cd") -> changeDir(command, fileSystemMap)
        }
    }

    private fun changeDir(command: String, fileSystemMap: FileSystemMap) {
        val (_, _, dir) = command.split(" ")
        when (dir) {
            ".." -> fileSystemMap.currentDirectory = fileSystemMap.currentDirectory.parent ?: throw IllegalArgumentException()
            else -> fileSystemMap.currentDirectory = fileSystemMap.currentDirectory.directories.find { it.name == dir } ?: throw IllegalArgumentException()
        }
    }

    private fun parseLsLines(lsLines: List<String>, fileSystemMap: FileSystemMap) {
        lsLines.forEach {
            when {
                it.startsWith("dir") -> parseDir(it, fileSystemMap)
                it.isNotBlank() -> parseFile(it, fileSystemMap)
            }
        }
    }

    private fun parseFile(file: String, fileSystemMap: FileSystemMap) {
        val (size, name) = file.split(" ")
        fileSystemMap.currentDirectory.files.add(File(name, size.toLong()))
    }

    private fun parseDir(dir: String, fs: FileSystemMap) {
        val (_, name) = dir.split(" ")
        fs.currentDirectory.directories.add(Directory(name, fs.currentDirectory))
    }

    private fun extractLsLines(firstIndex: Int, input: List<String>): List<String> {
        return input.subList(firstIndex, input.size).takeWhile { s -> !s.startsWith("$") }
    }

}
