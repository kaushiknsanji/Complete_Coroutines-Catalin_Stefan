package flows.operators

/**
 * Code to demonstrate the use of buffer operator.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Collect the data stream whilst measuring the time taken
        val time = measureTimeMillis {
            generate()
                    .buffer() // Call to buffer fast emissions
                    .collect { // Receive the emissions
                        // Simulate the processing time of 300ms
                        delay(300L)
                        // Print the element received
                        println(it)
                    }
        }
        // Print the total time taken to emit and consume all the elements
        println("Collected in ${time}ms")
    }
}

/**
 * Method to generate numbers from 1 to 3, with a processing time of 100ms for each element emitted.
 */
fun generate() = (1..3).asFlow() // Create a Flow of 1 to 3 integers
        .onEach {
            // Apply a delay of 100ms on each element emitted, to simulate the processing time
            delay(100L)
        }