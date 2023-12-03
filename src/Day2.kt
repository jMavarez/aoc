import kotlin.math.max

fun main() {
    println("Day 2!")

    val maxRed = 12
    val maxGreen = 13
    val maxBlue = 14

    fun part1(input: List<String>): Int =
        input.fold(0) { acc, line ->
            val base = line.split(":")
            val gameId = base[0].split(" ")[1]
            val sets = base[1].split(";")
            var valid = true

            sets.map { set ->
                set.split(",").map { set2 ->
                    val s = set2.trim().split(" ")
                    when (s[1]) {
                        "red" -> if (s[0].toInt() > maxRed) valid = false
                        "green" -> if (s[0].toInt() > maxGreen) valid = false
                        "blue" -> if (s[0].toInt() > maxBlue) valid = false
                    }
                }
            }

            acc + (if (valid) gameId.toInt() else 0)
        }

    fun part2(input: List<String>): Int =
        input.fold(0) { acc, line ->
            val base = line.split(":")
            val sets = base[1].split(";")

            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0

            sets.map { set ->
                set.split(",").map { set2 ->
                    val s = set2.trim().split(" ")
                    when (s[1]) {
                        "red" -> maxRed = max(maxRed, s[0].toInt())
                        "green" -> maxGreen = max(maxGreen, s[0].toInt())
                        "blue" -> maxBlue = max(maxBlue, s[0].toInt())
                    }
                }
            }

            acc + (maxRed * maxGreen * maxBlue)
        }.also { println("Result: $it") }

    // Test
    val testInput = readInput("test_input/day2")
    check(part1(testInput) == 8) { "Test Part 1 Failed" }
    check(part2(testInput) == 2286) { "Test Part 2 Failed" }

    // Solve
    val input = readInput("input/day2")
    println("Part 1 Answer: " + part1(input))
    println("Part 2 Answer: " + part2(input))
} 