package channels

/**
 * Code to demonstrate how to use Channel Producer as an extension function on CoroutineScope
 * to send and receive values from a Channel.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Loop through all the values from the channel
        for (element in produceSquares()) {
            // Printing the square value received from the channel
            println(element)
        }
    }
}

/**
 * Extension function on [CoroutineScope] that sends the squares of the
 * values 1 to 5 over the channel.
 */
private fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    // Start a Coroutine with Producer Scope to send
    // the squares of values 1 to 5 over the channel
    (1..5).forEach {
        send(it * it)
    }
}