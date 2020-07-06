package flows.operators

/**
 * Code to demonstrate the Transform Operator function on a Flow.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Execute the transform operation
        transformOperator()
    }
}

/**
 * Suspending function that applies the given transformation to each of the value in the stream,
 * and then prints the resulting stream.
 */
suspend fun transformOperator() {
    // Create a Flow of 1 to 10 integers
    (1..10).asFlow()
            .transform {
                // Emit a String with the number
                emit("Emitting string value $it")
                // Emit the number itself
                emit(it)
            }
            .collect {
                // Print each element emitted by the flow.
                // This will include both the string and integer emitted for each integer in the source flow
                println(it)
            }
}
