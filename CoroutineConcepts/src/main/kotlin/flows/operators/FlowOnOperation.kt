package flows.operators

/**
 * Code to demonstrate the FlowOn Operator function on a Flow.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode, in the Default Dispatcher
    runBlocking(Dispatchers.Default) {
        // Execute the flowOn operation
        flowOnOperator()
    }
}

/**
 * Suspending function called on Default Dispatcher to print only the first three even values from the stream.
 */
suspend fun flowOnOperator() {
    // Create a Flow of 1 to 10 integers
    (1..10).asFlow()
            .filter {
                // Will be executed in IO
                // Filter even values only
                it % 2 == 0
            }
            .flowOn(Dispatchers.IO) // Context switched to IO for preceding operators
            .take(3) // Take first 3 values. Will be executed in Default
            .collect {
                println(it) // Prints the first 3 even values
            }
}
