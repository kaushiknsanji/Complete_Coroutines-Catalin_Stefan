package flows.operators

/**
 * Code to demonstrate how to combine two flows using Combine operator.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Combine the flows and print the resulting flow
        combineOperator()
    }
}

/**
 * Suspending function that Combines two flows based on their most recent values and prints the resulting flow.
 */
suspend fun combineOperator() {
    // Create a Flow of 1 to 5 integers with a delay of 300ms for each element
    val numbers = (1..5).asFlow()
            .onEach { delay(300L) }

    // Create a Flow of 1 to 5 English Numbers with a delay of 400ms for each element
    val numbersInWords = flowOf("One", "Two", "Three", "Four", "Five")
            .onEach { delay(400L) }

    // Combine the flows
    numbers.combine(numbersInWords) { no, word ->
        // Apply the transform to convert it to a meaningful string
        "$no -> $word"
    }
            .collect {
                // Print the elements
                println(it)
            }
}