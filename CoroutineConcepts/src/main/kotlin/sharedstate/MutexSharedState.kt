package sharedstate

/**
 * Code to illustrate the use of Mutex to solve the concurrent simultaneous update issue
 * on a shared state variable.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Shared state counter. Starts at 0.
        var counter = 0
        // Mutex lock for the Shared state counter
        val mutex = Mutex()
        // Switch to Default Dispatcher for computation process
        withContext(Dispatchers.Default) {
            massiveRun {
                // Do some parallel processing if any..

                // Start synchronized update on the Shared state counter using Mutex
                mutex.withLock {
                    counter++
                }
            }
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