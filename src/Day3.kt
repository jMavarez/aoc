fun main() {
    println("Day 3!")

    data class Item(val x: Int, val y: Int, val value: String)

    fun parseNumber(input: List<String>): List<Item> =
        input.flatMapIndexed { y, line ->
            "[0-9]+".toRegex().findAll(line).map {
                Item(x = it.range.first, y = y, value = it.value)
            }
        }

    fun parseSymbols(input: List<String>): List<Item> =
        input.flatMapIndexed { y, line ->
            "[^0-9.]".toRegex().findAll(line).map {
                Item(x = it.range.first, y = y, value = it.value)
            }
        }.filter { it.value != "." }

    fun isAdjacent(number: Item, symbol: Item): Boolean =
        symbol.x in number.x - 1..number.x + number.value.length &&
                symbol.y in number.y - 1..number.y + 1

    fun part1(input: List<String>): Int {
        val numbers = parseNumber(input)
        val symbols = parseSymbols(input)
        return numbers
            .filter { n -> symbols.any { s -> isAdjacent(n, s) } }
            .sumOf { it.value.toInt() }
    }

    fun part2(input: List<String>): Int {
        val numbers = parseNumber(input)
        val symbols = parseSymbols(input)
        return symbols
            .filter { it.value == "*" }
            .sumOf { s ->
                val adjacent = numbers.filter { isAdjacent(it, s) }.map { it.value.toInt() }
                if (adjacent.size == 2) adjacent[0] * adjacent[1] else 0
            }
    }

    // Test
    val testInput = readInput("test_input/day3")
    check(part1(testInput) == 4361) { "Test Part 1 Failed" }
    check(part2(testInput) == 467835) { "Test Part 2 Failed" }

    // Solve
    val input = readInput("input/day3")
    println("Part 1 Answer: " + part1(input))
    println("Part 2 Answer: " + part2(input))
} 