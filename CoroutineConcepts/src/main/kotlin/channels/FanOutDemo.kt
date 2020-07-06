package channels

/**
 * Code to demonstrate the Fan-out principle of multiple Coroutines receiving elements from the same Channel.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Produce infinite numbers over a Channel
        val numbers = produceNumbers()
        // Start 5 Coroutines that receive numbers from the same [numbers] channel
        repeat(5) {
            launchProcessor(it, numbers)
        }

        // Cancel receiving further elements after 1.1s
        delay(1100)
        numbers.cancel()
    }
}

/**
 * Extension function on [CoroutineScope] that sends infinite numbers starting from 1 over a channel,
 * with a fixed delay for each of the elements to simulate the time taken to generate each element.
 *
 * @return Instance of [ReceiveChannel] that contains a stream of numbers.
 */
private fun CoroutineScope.produceNumbers(): ReceiveChannel<Int> = produce {
    // Start a Coroutine with Producer Scope to send infinite numbers
    var x = 1 // Starting from 1
    while (true) {
        // Delay for 100ms on each element to simulate the time taken to generate each element
        delay(100)
        // Incrementing and sending the result over the channel
        send(x++)
    }
}

/**
 * Extension function on [CoroutineScope] that receives infinite numbers from the [numbers] channel
 * and prints the [Id] of the Coroutine with the number received from the [numbers] channel.
 */
private fun CoroutineScope.launchProcessor(Id: Int, numbers: ReceiveChannel<Int>) = launch {
    numbers.consumeEach {
        println("Processor $Id received $it")
    }
}