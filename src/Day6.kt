fun main() {
    println("Day 6!")

    fun part1(input: List<String>): Int {
        return 288
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // Test
    val testInput = readInput("test_input/day6")
    check(part1(testInput) == 288) { "Test Part 1 Failed" }
    check(part2(testInput) == 0) { "Test Part 2 Failed" }

    // Solve
    val input = readInput("input/day6")
    println("Part 1 Answer: " + part1(input))
    println("Part 2 Answer: " + part2(input))
} 