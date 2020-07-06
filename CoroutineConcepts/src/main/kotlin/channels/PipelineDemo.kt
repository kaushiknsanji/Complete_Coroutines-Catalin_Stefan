package channels

/**
 * Code to demonstrate how to use the Pipeline pattern with multiple channels to feed
 * the output of one channel as the input to the other, and receive them in the end.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Produce infinite numbers over a Channel
        val numbers = produceNumbers()
        // Pipeline the numbers from the above channel into another channel to produce the squares of those values
        val squares = squares(numbers)
        // Receive the first 5 elements from the squares channel and print their result
        (1..5).forEach {
            println(squares.receive())
        }

        println("Done!")

        // Cancel all children coroutines started for the channels
        coroutineContext.cancelChildren()
    }
}

/**
 * Extension function on [CoroutineScope] that sends infinite numbers starting from 1 over a channel.
 *
 * @return Instance of [ReceiveChannel] that contains a stream of numbers.
 */
private fun CoroutineScope.produceNumbers(): ReceiveChannel<Int> = produce {
    // Start a Coroutine with Producer Scope to send infinite numbers
    var x = 1 // Starting from 1
    while (true) {
        send(x++) // Incrementing and sending the result over the channel
    }
}

/**
 * Extension function on [CoroutineScope] that sends the squares of the numbers received from the [numbers] channel,
 * over a new channel.
 *
 * @return Instance of [ReceiveChannel] that contains the squares of the [numbers].
 */
private fun CoroutineScope.squares(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    // Start a Coroutine with Producer Scope to send squares of the received numbers
    for (x in numbers) {
        send(x * x) // Compute the square and send the result over the channel
    }
}