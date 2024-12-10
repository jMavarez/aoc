import kotlin.math.abs

fun main() {
    println("Day 2!")

    fun isSafe(index: Int, tendency: Int, levels: List<Int>): Boolean {
        if (index + 1 >= levels.size) {
            return true
        }

        val a = levels[index]
        val b = levels[index + 1]

        val dist = a - b
        val ten = if (a - b < 0) 1 else -1

        if (dist < 0 && tendency == -1 || dist > 0 && tendency == 1) {
            return false
        }

        if (abs(dist) > 3 || dist == 0) {
            return false
        }

        return isSafe(index + 1, ten, levels)
    }

    fun part1(input: List<String>): Int =
        input
            .map { it.split(" ").map(String::toInt) }
            .count { isSafe(0, 0, it) }

    fun part2(input: List<String>): Int {
        val reports = input.map { it.split(" ").map(String::toInt) }
        val safeReports = reports.count { isSafe(0, 0, it) }
        val unsafeReports = reports.filterNot { isSafe(0, 0, it) }

        var dampenedReports = 0

        for (report in unsafeReports) {
            for (i in report.indices) {
                val newList = report.toMutableList().apply { removeAt(i) }
                if (isSafe(0, 0, newList)) {
                    dampenedReports++
                    break
                }
            }
        }

        return safeReports + dampenedReports
    }

// Test
    val testInput = readInput("test_input/day2")
    check(part1(testInput) == 2) { "Test Part 1 Failed" }
    check(part2(testInput) == 4) { "Test Part 2 Failed" }

    val input = readInput("input/day2")
    println("Part 1 Answer: " + part1(input))
    println("Part 2 Answer: " + part2(input))
} 