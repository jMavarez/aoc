import kotlin.math.abs

fun main() {
    println("Day 1!")

    fun part1(input: List<String>): Int {
        val listA = mutableListOf<Int>()
        val listB = mutableListOf<Int>()

        input.forEach { line ->
            val split = line.split("\\s+".toRegex()).map { it.toInt() }
            listA.add(split[0])
            listB.add(split[1])
        }

        listA.sort()
        listB.sort()

        var dist = 0

        for (i in 0..<listA.size) {
            dist += abs(listA[i] - listB[i])
        }

        return dist
    }

    fun part2(input: List<String>): Int {
        val listA = mutableListOf<Int>()
        val listB = mutableListOf<Int>()

        input.forEach { line ->
            val split = line.split("\\s+".toRegex()).map { it.toInt() }
            listA.add(split[0])
            listB.add(split[1])
        }

        var count = 0

        listA.forEach { a ->
            var seen = 0
            listB.forEach { b ->
                if (a == b) seen++
            }
            count += (a * seen)
        }

        return count
    }

    // Test
    val testInput = readInput("test_input/day1")
    check(part1(testInput) == 11) { "Test Part 1 Failed" }
    check(part2(testInput) == 31) { "Test Part 2 Failed" }

    // Solve
    val input = readInput("input/day1")
    println("Part 1 Answer: " + part1(input))
    println("Part 2 Answer: " + part2(input))
} 