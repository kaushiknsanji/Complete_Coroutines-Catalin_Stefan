package channels

/**
 * Code to demonstrate the setup and functioning of a Buffered Channel.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Create a Buffered Channel with capacity of 4 elements
        val channel = Channel<Int>(4)
        // Start a Coroutine and save its Job instance
        val senderJob = launch {
            // Send 10 elements over the channel to exceed the capacity
            repeat(10) {
                channel.send(it)
                println("Sent $it")
            }
        }

        // Consume three elements from the channel with a delay of 1 second for each
        repeat(3) {
            delay(1000)
            println("Received ${channel.receive()}")
        }

        // Cancel the Coroutine Job
        senderJob.cancel()
    }
}