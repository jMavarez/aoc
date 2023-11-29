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
        // Optional, if not provided will look to that last created file and increment the day.
        val day: Int = properties["day"].toString().toIntOrNull() ?: 0
        // Optional, if not provided an empty file will be created.
        val sourceDir = "$projectDir/src"
        val inputDir = "$sourceDir/input"
        val testInputDir = "$sourceDir/test_input"

        val prevDayNum = fileTree(sourceDir)
            .matching { include("Day*.kt") }
            .maxOfOrNull {
                Regex("Day(\\d+)\\.kt").find(it.name)?.destructured
                    ?.let { (value) -> value.toInt() } ?: 0
            } ?: 0
        val newDayNum = String.format("%d", prevDayNum.inc())

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
    check(part1(testInput) == 0)

    // Solve
    val input = readInput("input/day$newDayNum")
    print("Part 1 Answer: ")
    println(part1(input))    
    print("Part 2 Answer: ")
    println(part2(input))
} 
        """.trimIndent()
        )

        // Create test files.
        File(testInputDir, "day$newDayNum.txt").createNewFile()
        File(inputDir, "day$newDayNum.txt").createNewFile()
    }
}