package sharedstate

/**
 * Code to illustrate the concurrent update issue on a shared state variable caused when multiple coroutines
 * running in parallel try to update the same variable simultaneously.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Shared state counter. Starts at 0.
        var counter = 0
        // Switch to Default Dispatcher for computation process
        withContext(Dispatchers.Default) {
            // Start concurrent update on the Shared state counter
            massiveRun { counter++ }
        }
        // Print the updated value of the Shared state counter
        println("Counter = $counter")
    }
}

/**
 * Executes the given [action] using several coroutines running in parallel.
 */
private suspend fun massiveRun(action: suspend () -> Unit) {
    // 100 Coroutines
    val noOfCoroutines = 100
    // 1000 times each coroutine executes the given [action]
    val noOfTimesToExecuteAction = 1000

    // Get the value of the total execution time
    val time = measureTimeMillis {
        // Create a CoroutineScope to start multiple coroutines
        coroutineScope {
            // Start multiple coroutines to run in parallel
            repeat(noOfCoroutines) {
                launch {
                    // On each coroutine, execute the given [action] multiple times
                    repeat(noOfTimesToExecuteAction) {
                        action()
                    }
                }
            }
        }
    }

    // Print the total number of executions completed on the given [action] with the total time taken
    println("Completed ${noOfCoroutines * noOfTimesToExecuteAction} actions in ${time}ms")
}