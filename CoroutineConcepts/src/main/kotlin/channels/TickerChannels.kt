package channels

/**
 * Code to demonstrate the setup and functioning of a Ticker Channel.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Create a Ticker Channel with a delay of 100ms between Units
        val tickerChannel: ReceiveChannel<Unit> = ticker(delayMillis = 100).apply {
            // Start a Coroutine to receive the Units
            launch {
                // Get the current time in millis
                val startTime = System.currentTimeMillis()
                // Start consuming each of the Units produced
                consumeEach {
                    // Calculate the Time difference
                    val delta = System.currentTimeMillis() - startTime
                    // Print the tick received
                    println("Received tick after $delta")
                }
            }
        }

        // Cancel the channel after running for a second
        delay(1000)
        println("Done!")
        tickerChannel.cancel()
    }
}