package basics

/**
 * Code to demonstrate how to switch between contexts using `withContext` function.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Start a Coroutine in Default Dispatcher
        launch(Dispatchers.Default) {
            // Print the current context
            println("First context: $coroutineContext")

            // Switch to a different Dispatcher and start a Coroutine
            withContext(Dispatchers.IO) {
                // Print the current context
                println("Second context: $coroutineContext")
            }

            // Print the current context
            println("Third context: $coroutineContext")
        }
    }
}