package flows

/**
 * Code to demonstrate that `Flows` are transparent to cancellations.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Get the Flow stream to the numbers being emitted
        val numbersFlow = sendNumbers()
        println("Starting the flow now")
        // Start a Timeout Coroutine that lasts for only a second
        // (This is timed to 1 second since emission of all the values takes 1.2seconds, and we want to
        // simulate cancellation of Flow caused by the cancellation of its coroutine)
        withTimeoutOrNull(1000L) {
            // Print the numbers received
            // (Only 1 and 2 will be printed. Coroutine will be cancelled early by 200ms w.r.t. the last emission)
            numbersFlow.collect { println(it) }
        }
    }
}

/**
 * Method that creates and returns a [Flow] for emitting numbers, that takes a total time of 1.2seconds.
 */
private fun sendNumbers() = flow {
    listOf(1, 2, 3).forEach {
        delay(400L) // pause for 400ms
        emit(it) // Emit the number
    }
}