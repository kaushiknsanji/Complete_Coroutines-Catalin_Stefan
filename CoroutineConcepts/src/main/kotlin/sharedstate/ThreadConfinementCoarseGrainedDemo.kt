package sharedstate

/**
 * Code to illustrate the use of Thread confinement technique by Coarse grained approach to solve the concurrent
 * simultaneous update issue on a shared state variable.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Shared state counter. Starts at 0.
        var counter = 0
        // Single Threaded Dispatcher Context for updating the Shared state counter
        val counterContext = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
        // Switch to Single Threaded Context to update the Shared state counter
        withContext(counterContext) {
            // (Everything here runs in a single thread)
            // Do some computation if any (works serially in this approach)..

            // After computation, update the Shared state counter
            massiveRun {
                counter++
            }
        }
        // Print the updated value of the Shared state counter
        println("Counter = $counter")
        // Close the Single threaded context
        counterContext.close()
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