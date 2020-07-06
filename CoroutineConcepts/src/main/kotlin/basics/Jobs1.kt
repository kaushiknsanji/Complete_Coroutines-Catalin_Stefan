package basics

/**
 * Code to show how a Job can be created and how its lifecycle methods can be used.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Start a Coroutine and save its Job instance
        val job1 = launch {
            println("Job1 launched")
        }

        // Using the Job instance, execute the following block after the Completion of Job
        // This can be invoked in cases where the Job completes or gets canceled
        job1.invokeOnCompletion {
            println("Job1 completed")
        }
    }
}