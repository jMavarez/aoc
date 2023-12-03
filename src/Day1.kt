fun main() {
    println("Day 1!")

    fun part1(input: List<String>): Int =
        input.fold(0) { acc, s ->
            val ln = s.filter { it.isDigit() }
            val ns = if (ln.length > 1) ln else ln + ln
            val ni = ns[0] + ns.takeLast(1)
            acc + ni.toInt()
        }

    fun calibrate(row: String): Int =
        "${row.first { it.isDigit() }}${row.last { it.isDigit() }}".toInt()

    fun part1Kotlin(input: List<String>) {
        input.sumOf { calibrate(it) }.also {
            println("Part 1 The Kotlin way: $it")
        }
    }

    val numberMap = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    fun wordSize(string: String, startAt: Int): Pair<Int, Int?> {
        // TODO: This needs to handle the case where two number are close to each other eg. twone.
        // In that case we should pick one instead of two.
        for ((key, value) in numberMap) {
            val candidate = string.substring(startAt, (startAt + key.length).coerceAtMost(string.length))
            if (candidate == key) {
                return key.length to value
            }
        }
        return 0 to 0
    }

    fun part2(input: List<String>): Int {


        return input.fold(0) { acc, s ->
            var i = 0
            var ns = ""
            while (i < s.length) {
                val c = s[i]
                if (c.isDigit()) {
                    // Take digit.
                    ns += c
                    i++
                } else {
                    val (idx, vl) = wordSize(s, i)
                    if (idx <= 0) {
                        i++
                    } else {
                        ns += vl.toString()
                        i += idx
                    }
                }
            }
            val int = ("${ns.first()}${ns.last()}").toInt()
//            println("$s $int")
            acc + int
        }
    }

    fun wordsAt(string: String, index: Int): List<String> =
        (3..5).map { len ->
            string.substring(index, (index + len).coerceAtMost(string.length))
        }

    fun part2Kotlin(input: List<String>): Int =
        input.sumOf { row ->
            calibrate(
                row.mapIndexedNotNull { index, c ->
                    if (c.isDigit()) c
                    else wordsAt(row, index).firstNotNullOfOrNull {
                        numberMap[it]
                    }
                }.joinToString()
            )
        }.also { println("Part 2 The Kotlin way: $it") }

    // Test
    val testInput = readInput("test_input/day1")
    check(part1(testInput) == 142) { "Test Part 1 Failed" }
    val testInput2 = readInput("test_input/day1_b")
    check(part2(testInput2) == 281) { "Test Part 2 Failed" }

    // Solve
    val input = readInput("input/day3")
    println("Part 1 Answer: " + part1(input))
    println("Part 2 Answer: " + part2(input))

    part1Kotlin(input)
    part2Kotlin(input)
}