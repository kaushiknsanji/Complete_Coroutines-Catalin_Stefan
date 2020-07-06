package basics

/**
 * Code to test starting a million Coroutines.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Starting a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Executing following block a million times
        repeat(1_000_000) {
            // Launching a Coroutine
            launch {
                print(".") // Prints a '.'
            }
        }
    }

}