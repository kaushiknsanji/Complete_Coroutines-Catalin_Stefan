package flows

/**
 * Code to demonstrate that Flows are cold streams.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Get the Flow stream to the numbers being emitted
        val numbersFlow = sendNumbers()
        println("Flow has not started yet")
        println("Starting the flow now")

        // Starts receiving the numbers only on collect call
        numbersFlow.collect { println(it) }
    }
}

/**
 * Method that creates and returns a [Flow] for emitting numbers.
 */
private fun sendNumbers(): Flow<Int> = listOf(1, 2, 3).asFlow()