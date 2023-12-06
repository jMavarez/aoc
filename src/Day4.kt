import kotlin.math.pow

fun main() {
    println("Day 4!")

    data class Card(val id: Int, val numbers: List<String>, val winningNumbers: List<String>)

    fun parse(input: List<String>): List<Card> = input.map { line ->
        val cardId = "Card ([0-9]+):".toRegex().find(line)?.groupValues?.get(1) ?: "0"
        val (n, wn) = line.substringAfter(":").split("|").map { it.trim() }
        Card(id = cardId.toInt(),
            numbers = n.trim().split(" "),
            winningNumbers = wn.trim().split(" ").filter { it.isNotEmpty() })
    }

    fun countOccurrences(card: Card): Int =
        card.winningNumbers.toSet().intersect(card.numbers.toSet()).size

    fun part1(input: List<String>): Int =
        parse(input)
            .map { countOccurrences(it) }.filter { it != 0 }
            .sumOf { c -> if (c == 1) c else (2.0).pow(c - 1).toInt() }

    fun part2(input: List<String>): Int =
        parse(input)
            .map { countOccurrences(it) }
            .let { counts ->
                val map = mutableMapOf<Int, Int>()
                counts.indices.forEach { index -> map[index + 1] = 1 }
                counts.forEachIndexed { index, occurrences ->
                    (index + 2..index + occurrences + 1).forEach {
                        map[it] = (map[it] ?: 0) + (map[index + 1] ?: 0)
                    }
                }
                map.values.sum()
            }

    // Test
    val testInput = readInput("test_input/day4")
    check(part1(testInput) == 13) { "Test Part 1 Failed" }
    check(part2(testInput) == 30) { "Test Part 2 Failed" }

    // Solve
    val input = readInput("input/day4")
    println("Part 1 Answer: " + part1(input))
    println("Part 2 Answer: " + part2(input))
}