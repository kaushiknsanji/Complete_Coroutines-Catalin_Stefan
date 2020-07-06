package basics

/**
 * Code to demonstrate the use of 'async' Coroutine builder.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Start a Coroutine to get the Deferred result of first value
        val firstValueDeferred = async { getFirstValue() }
        // Start a Coroutine to get the Deferred result of second value
        val secondValueDeferred = async { getSecondValue() }

        // Simulating processing for half a second
        println("Doing some processing here..")
        delay(500L)
        println("Waiting for values..")

        // Wait for the first value. This takes about a second to complete
        val firstValue = firstValueDeferred.await()
        // Wait for the second value. This takes about two seconds to complete
        val secondValue = secondValueDeferred.await()
        // Compute the sum of two values
        println("The total is ${firstValue + secondValue}")
    }
}

/**
 * Suspend function to compute and provide the first random value.
 */
suspend fun getFirstValue(): Int {
    // Simulate processing time of one second
    delay(1000L)
    // Get some random value from 0 until 100
    val firstValue = Random.nextInt(100)
    // Print the random value obtained
    println("Returning first value $firstValue")
    // Return the random value
    return firstValue
}

/**
 * Suspend function to compute and provide the second random value.
 */
suspend fun getSecondValue(): Int {
    // Simulate processing time of two seconds
    delay(2000L)
    // Get some random value from 0 until 1000
    val secondValue = Random.nextInt(1000)
    // Print the random value obtained
    println("Returning second value $secondValue")
    // Return the random value
    return secondValue
}