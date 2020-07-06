package channels

/**
 * Code to demonstrate how to send and receive elements from a Coroutine Channel after issuing
 * a close token on the channel.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Create a Channel
        val channel = Channel<Int>()
        // Start a new Coroutine
        launch {
            // For 1 to 5, publish the square of it to the channel
            (1..5).forEach {
                channel.send(it * it)
            }
            // Closing the channel
            channel.close()
        }

        // Loop through all the values from the channel
        for (element: Int in channel) {
            // Printing the square value received from the channel
            println(element)
        }
    }
}