package channels

/**
 * Code to demonstrate the Fan-In principle of multiple Coroutines sending elements to the same Channel.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Create a Channel
        val channel = Channel<String>().apply {
            // Start a Coroutine to send the message "Message1" with a delay of 200ms
            launch { sendMessage("Message1", 200) }
            // Start a Coroutine to send the message "Message2" with a delay of 500ms
            launch { sendMessage("Message2", 500) }
        }
        // Repeat 6 times to receive six messages from the Channel
        repeat(6) {
            // Print the message received
            println("$it: ${channel.receive()}")
        }

        // Cancel all children coroutines started for the channels
        coroutineContext.cancelChildren()
    }
}

/**
 * Suspending Extension function on [Channel] to keep sending the given [message]
 * over the [Channel] with a delay of [delayInMillis].
 */
suspend fun Channel<String>.sendMessage(message: String, delayInMillis: Long) {
    while (true) {
        // Delay for the [delayInMillis] mentioned
        delay(delayInMillis)
        // Send the given [message] over the channel
        send(message)
    }
}