package flows.operators

/**
 * Code to demonstrate the Map Operator function on a Flow.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Execute the map operation
        mapOperator()
    }
}

/**
 * Suspending function that maps each integer in the original stream to a [String],
 * and then prints the resulting stream.
 */
suspend fun mapOperator() {
    // Create a Flow of 1 to 10 integers
    (1..10).asFlow()
            .map {
                // Pause for half a second
                delay(500L)
                // Map to a String and return
                "mapping $it"
            }.collect {
                // Print the element
                println(it)
            }
}