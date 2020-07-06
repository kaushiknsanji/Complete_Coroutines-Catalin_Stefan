package flows.exceptions

/**
 * Code to demonstrate how an exception in a Flow can be handled using onCompletion operator.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Execute a Flow that throws exception and handle the same
        onCompletionOperator()
    }
}

/**
 * Suspending function that throws exception in a Flow and handles the same using onCompletion operator.
 */
suspend fun onCompletionOperator() {
    // Create a Flow of 1 to 3 integers
    (1..3).asFlow()
            .onEach { check(it != 2) } // Throw exception if the number is other than 2
            .onCompletion { cause: Throwable? ->
                // On Completion of the flow
                if (cause != null) {
                    // Catch exception if any and handle the same
                    println("Flow completed with exception $cause")
                } else {
                    // Print completion when the flow ends
                    println("Flow completed successfully")
                }
            }
            .catch { e -> println("Caught exception $e") } // Catches exceptions in onEach action lambda
            .collect { println(it) } // Print the elements
}