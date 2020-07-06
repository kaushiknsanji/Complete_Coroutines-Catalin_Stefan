package flows

/**
 * Code to demonstrate how a `Flow` can be constructed using its other builders.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        println("Receiving numbers")
        // Receive numbers from the flow
        sendNumbers().collect {
            // Print the number
            println("Received number: $it")
        }

        println("\nReceiving numbers in words")
        // Receive number-words from the flow
        sendNumbersInWords().collect {
            // Print the number
            println("Received number: $it")
        }
    }
}

/**
 * Method that creates and returns a [Flow] for emitting numbers.
 */
private fun sendNumbers(): Flow<Int> = listOf(1, 2, 3).asFlow()

/**
 * Method that creates and returns a [Flow] for emitting numbers in words.
 */
private fun sendNumbersInWords(): Flow<String> = flowOf("One", "Two", "Three")