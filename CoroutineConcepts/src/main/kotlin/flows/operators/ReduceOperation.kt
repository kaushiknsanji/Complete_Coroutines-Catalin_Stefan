package flows.operators

/**
 * Code to demonstrate the Reduce Operator function on a Flow.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Execute the reduce operation
        reduceOperator()
    }
}

/**
 * Suspending function that accumulates the values of the stream to find its factorial.
 */
suspend fun reduceOperator() {
    // Finding factorial of 10
    val size = 10
    val factorial = (1..size).asFlow() // Create a Flow of 1 to 10 integers
            .reduce { accumulator, value ->
                // Accumulate the values by multiplying it with the current accumulator value
                accumulator * value
            }
    // Print the factorial
    println("Factorial of $size is $factorial")
}