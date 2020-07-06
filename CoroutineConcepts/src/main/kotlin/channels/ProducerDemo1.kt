package channels

/**
 * Code to demonstrate how to use Channel Producer to send and receive values from a Channel.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        val channel: ReceiveChannel<Int> = produce {
            // Start a Coroutine with Producer Scope to send
            // the squares of values 1 to 5 over the channel
            (1..5).forEach {
                send(it * it)
            }
        }

        // Loop through all the values from the channel
        for (element in channel) {
            // Printing the square value received from the channel
            println(element)
        }
    }
}