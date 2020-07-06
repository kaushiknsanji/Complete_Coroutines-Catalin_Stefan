package flows.operators

/**
 * Code to demonstrate how to combine two flows using Zip operator.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Zip the flows and print the resulting flow
        zipOperator()
    }
}

/**
 * Suspending function that Zips two flows and prints the resulting flow.
 */
suspend fun zipOperator() {
    // Create a Flow of English Numbers
    val english = flowOf("One", "Two", "Three")
    // Create a Flow of French Numbers
    val french = flowOf("Un", "Deux", "Troix")
    // Zip the flows
    english.zip(french) { eng, fr ->
        // Apply the transform to convert it to a meaningful string
        "'$eng' in French is '$fr'"
    }.collect {
        // Print the elements
        println(it)
    }
}