package gr.alx.adventofcode.day7

import gr.alx.adventofcode.ReadInputHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FileSizeCalculatorTest {

    @Test
    fun `should parse cd to root command`() {
        val input = "$ cd /"

        val fileSystem = FileSystemMapper().map(input.lines())

        assertThat(fileSystem).isNotNull
        assertThat(fileSystem.rootDirectory.name).isEqualTo("/")
        assertThat(fileSystem.currentDirectory.name).isEqualTo("/")
    }

    @Test
    fun `should parse ls command and add top level files and dirs and calculate total size of root`() {
        val input = """
            ${'$'} cd /
            ${'$'} ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
        """.trimIndent()

        val fileSystem = FileSystemMapper().map(input.lines())

        assertThat(fileSystem).isNotNull
        assertThat(fileSystem.rootDirectory.files).extracting("name").contains("b.txt", "c.dat")
        assertThat(fileSystem.rootDirectory.directories).extracting("name").contains("a", "d")
        assertThat(fileSystem.rootDirectory.totalSize()).isEqualTo(23352670L)
    }

    @Test
    fun `should navigate to sibling directory`() {
        val input = """
            ${'$'} cd /
            ${'$'} ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            ${'$'} cd a
        """.trimIndent()

        val fileSystem = FileSystemMapper().map(input.lines())

        assertThat(fileSystem.currentDirectory.name).isEqualTo("a")

    }

    @Test
    fun `should navigate to parent directory`() {
        val input = """
            ${'$'} cd /
            ${'$'} ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            ${'$'} cd a
            ${'$'} cd ..
        """.trimIndent()

        val fileSystem = FileSystemMapper().map(input.lines())

        assertThat(fileSystem.currentDirectory.name).isEqualTo("/")

    }

    @Test
    fun `should parse test input`() {
        val input = """
            ${'$'} cd /
            ${'$'} ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            ${'$'} cd a
            ${'$'} ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            ${'$'} cd e
            ${'$'} ls
            584 i
            ${'$'} cd ..
            ${'$'} cd ..
            ${'$'} cd d
            ${'$'} ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent()

        val fileSystem = FileSystemMapper().map(input.lines())

        assertThat((fileSystem.sumOfDirsWithMaxSize(100000))).isEqualTo(95437)
    }

    @Test
    fun `should parse input`() {
        val input = ReadInputHelper.readTextFromResource("/day7/cmd.txt")

        val fileSystem = FileSystemMapper().map(input.lines())

        assertThat((fileSystem.sumOfDirsWithMaxSize(100000))).isEqualTo(1)
    }

    @Test
    fun `should parse test input for part 2`() {
        val input = """
            ${'$'} cd /
            ${'$'} ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            ${'$'} cd a
            ${'$'} ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            ${'$'} cd e
            ${'$'} ls
            584 i
            ${'$'} cd ..
            ${'$'} cd ..
            ${'$'} cd d
            ${'$'} ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent()

        val fileSystem = FileSystemMapper().map(input.lines())

        assertThat((fileSystem.chooseDirToDelete(30000000)).totalSize()).isEqualTo(24933642)
    }

    @Test
    fun `should parse input for part 2`() {
        val input = ReadInputHelper.readTextFromResource("/day7/cmd.txt")

        val fileSystem = FileSystemMapper().map(input.lines())

        assertThat((fileSystem.chooseDirToDelete(30000000)).totalSize()).isEqualTo(1)
    }

}
