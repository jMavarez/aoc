plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    test {
        useJUnitPlatform()
    }
}

kotlin {
    jvmToolchain(8)
}

task("genNextDay") {
    doLast {
        val sourceDir = "$projectDir/src"
        val inputDirFile = File("$sourceDir/input").apply {
            mkdirs()
        }
        val testInputDirFile = File("$sourceDir/test_input").apply {
            mkdirs()
        }

        val prevDayNum = fileTree(sourceDir)
            .matching { include("Day*.kt") }
            .maxOfOrNull {
                "Day(\\d+).kt".toRegex().find(it.name)?.destructured
                    ?.let { (value) -> value.toInt() } ?: 0
            }?.inc() ?: 1
        val newDayNum = String.format("%d", prevDayNum)

        // Create new Day/kt file.
        File(sourceDir, "Day$newDayNum.kt").writeText(
            """
fun main() {
    println("Day $newDayNum!")

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // Test
    val testInput = readInput("test_input/day$newDayNum")
    check(part1(testInput) == 0) { "Test Part 1 Failed" }
    check(part2(testInput) == 0) { "Test Part 2 Failed" }

    // Solve
    val input = readInput("input/day$newDayNum")
    println("Part 1 Answer: " + part1(input))
    println("Part 2 Answer: " + part2(input))
} 
        """.trimIndent()
        )

        // Create test files.
        File(testInputDirFile, "day$newDayNum.txt").createNewFile()
        File(inputDirFile, "day$newDayNum.txt").createNewFile()
    }
}