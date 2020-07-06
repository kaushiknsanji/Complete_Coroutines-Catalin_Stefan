package basics

/**
 * Code to show how a Job can be created and canceled prematurely.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Start a Coroutine and save its Job instance
        val job1 = launch {
            delay(3000L) // delay by 3 seconds
            println("Job1 launched")
        }

        // Using the Job instance, execute the following block after the Completion of Job
        // This can be invoked in cases where the Job completes or gets canceled
        job1.invokeOnCompletion {
            println("Job1 completed")
        }

        delay(500L) // delay by half a second
        println("Job1 will be canceled")
        // Canceling the Job that was started, half a second later
        job1.cancel()
    }
}