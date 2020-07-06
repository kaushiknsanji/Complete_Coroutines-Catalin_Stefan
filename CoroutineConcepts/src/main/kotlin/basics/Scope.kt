package basics

/**
 * Code to test different Scopes.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.*

fun main() {
    println("program execution will now block")
    // Starting a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Start another Coroutine
        launch {
            delay(1000L) // Delay the start by 1 second
            println("Task from runBlocking")
        }

        // Start another Coroutine with Global Scope
        GlobalScope.launch {
            delay(500L) // Delay the start by 1/2 second
            println("Task from GlobalScope")
        }

        // Start a Coroutine with custom Coroutine Scope
        coroutineScope {
            launch {
                delay(1500L) // Delay the start by 1.5 seconds
                println("Task from coroutineScope")
            }
        }
    }
    println("program execution will now continue")
}