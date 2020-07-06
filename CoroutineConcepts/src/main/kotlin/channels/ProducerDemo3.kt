package channels

/**
 * Code to demonstrate how to use Channel Producer as an extension function on CoroutineScope
 * to send values over the channel and use consumeEach extension function on ReceiveChannel to
 * receive all the values from the Channel.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Consume each value from the channel and print the value received
        produceSquares().consumeEach {
            println(it)
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