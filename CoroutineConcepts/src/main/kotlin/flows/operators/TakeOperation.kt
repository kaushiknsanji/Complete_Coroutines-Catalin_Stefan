package flows.operators

/**
 * Code to demonstrate the Take Operator function on a Flow.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Execute the take operation
        takeOperator()
    }
}

/**
 * Suspending function that takes only the first two numbers from the original Flow,
 * and returns the same as a new Flow.
 */
suspend fun takeOperator() {
    // Create a Flow of 1 to 10 integers
    (1..10).asFlow()
            .take(2) // Take first 2 numbers from the flow
            .collect {
                println(it) // Print the numbers
            }
}