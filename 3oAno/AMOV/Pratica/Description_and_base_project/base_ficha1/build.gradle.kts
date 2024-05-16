import org.w3c.dom.Node
import org.w3c.dom.NodeList

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

tasks.test {
    useJUnitPlatform()
    maxHeapSize = "2048m"
    ignoreFailures = true // Add this line to ignore test failures
    testLogging {
        events("passed", "skipped", "failed")
    }
}
tasks.register("runTests") {
    dependsOn("test")
}

tasks.named("runTests") {
    doLast {
        val resultsDir = file("$buildDir/test-results/test")
        val testXmlFiles = resultsDir.listFiles { _, name -> name.endsWith(".xml") }

        if (testXmlFiles.isNullOrEmpty()) {
            println("No test results found.")
            return@doLast
        }

        // Define the test weights (adjust as needed)
        val testWeights = loadTestWeightsFromCsv("test_weights.csv")

        var passedTests = 0
        var failedTests = 0

        // Create a list to store the results
        val testResults = mutableListOf<Pair<String, String>>()

        testXmlFiles.forEach { xmlFile ->
            val suite = javax.xml.parsers.DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(xmlFile)

            val testCaseNodes = suite.getElementsByTagName("testcase")

            testCaseNodes.forEach { testCaseNode ->
                val testName = testCaseNode.attributes.getNamedItem("name").nodeValue
                val testStatus = testCaseNode.childNodes.length

                if (testStatus == 0) {
                    passedTests++
                    testResults.add(Pair(testName, "PASSED"))
                } else {
                    failedTests++
                    testResults.add(Pair(testName, "Failed"))
                    println("$testName failed:")
                }
            }
        }

        // Calculate the weighted score and final grade
        var weightedScore = 0.0

        testResults.forEach { testResult ->
            val testName = testResult.first
            val testStatus = testResult.second
            val testWeight = testWeights[testName]
            if (testWeight != null) {
                if (testStatus == "PASSED") {
                    weightedScore += testWeight
                }
            }
        }

        val finalGrade = (weightedScore).toInt()

        println("\nTests Passed: $passedTests")
        println("Tests Failed: $failedTests")
        println("Final Grade: $finalGrade%")

        if (failedTests == 0) {
            println("All tests passed successfully!")
        } else {
            println("Some tests failed. Check the test report for details.")
        }
    }
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

fun NodeList.forEach(action: (node: Node) -> Unit) {
    for (i in 0 until this.length) {
        action(this.item(i))
    }
}

// Load test weights from a CSV file
fun loadTestWeightsFromCsv(filename: String): Map<String, Double> {
    val csvFile = File(filename)
    if (!csvFile.exists()) {
        println("Test weights file '$filename' not found.")
        return emptyMap()
    }

    val testWeights = mutableMapOf<String, Double>()

    try {
        csvFile.bufferedReader().use { reader ->
            val lines = reader.readLines()
            for (line in lines.drop(1)) { // Skip the header row
                val parts = line.split(',')
                if (parts.size >= 2) {
                    val testName = parts[0].trim()
                    val weight = parts.drop(1).joinToString().toDoubleOrNull() ?: 0.0
                    testWeights[testName] = weight
                }
            }
        }
    } catch (e: Exception) {
        println("Error loading test weights: ${e.message}")
    }

    return testWeights
}
