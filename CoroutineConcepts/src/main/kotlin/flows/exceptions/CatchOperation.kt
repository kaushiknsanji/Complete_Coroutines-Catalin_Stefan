package flows.exceptions

/**
 * Code to demonstrate how an exception in a Flow can be handled using catch operator.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Execute a Flow that throws exception and handle the same
        catchOperator()
    }
}

/**
 * Suspending function that throws exception in a Flow and handles the same using catch operator.
 */
suspend fun catchOperator() {
    // Create a Flow of 1 to 3 integers
    (1..3).asFlow()
            .onEach { check(it != 2) } // Throw exception if the number is other than 2
            .catch { e -> println("Caught exception $e") } // Catches exceptions in onEach action lambda
            .collect { println(it) } // Print the elements
}