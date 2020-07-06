package flows

/**
 * Code to demonstrate how a `Flow` is constructed, emits and collects values.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        println("Receiving prime numbers")
        // Receive prime numbers from the flow
        sendPrimes().collect {
            // Print the prime number
            println("Received prime number $it")
        }
        println("Finished receiving numbers")
    }
}

/**
 * Method that creates and returns a [Flow] for emitting Prime numbers.
 */
fun sendPrimes(): Flow<Int> = flow {
    // Fixed list of prime numbers to emit
    val primesList = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
    // Iterate over the list of prime numbers
    primesList.forEach {
        // Simulate processing time for each prime number
        delay(it * 100L)
        // Emit the Prime number
        emit(it)
    }
}