package flows.operators

/**
 * Code to demonstrate the Filter Operator function on a Flow.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Execute the filter operation
        filterOperator()
    }
}

/**
 * Suspending function that filters a stream of integers for even numbers only,
 * and then prints the resulting stream.
 */
suspend fun filterOperator() {
    // Create a Flow of 1 to 10 integers
    (1..10).asFlow()
            .filter {
                // Pause for half a second
                delay(500L)
                // Filter even values only
                it % 2 == 0
            }.collect {
                // Print the element
                println(it)
            }
}