package basics

/**
 * Code to show how a Job can be created with hierarchy and canceled prematurely.
 * Whole hierarchy gets canceled when parent job is canceled.
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
            println("Job1 launched")
            // Start another Coroutine and save its Job instance
            val job2 = launch {
                println("Job2 launched")
                delay(3000L) // delay by 3 seconds
                println("Job2 is finishing")
            }
            // Using the job2 instance, execute the following block after its completion
            // (Can be invoked in cases where the Job completes or gets canceled)
            job2.invokeOnCompletion {
                println("Job2 completed")
            }

            // Start another Coroutine and save its Job instance
            val job3 = launch {
                println("Job3 launched")
                delay(3000L) // delay by 3 seconds
                println("Job3 is finishing")
            }
            // Using the job3 instance, execute the following block after its completion
            // (Can be invoked in cases where the Job completes or gets canceled)
            job3.invokeOnCompletion {
                println("Job3 completed")
            }
        }

        // Using the job1 instance, execute the following block after its completion
        // (Can be invoked in cases where the Job completes or gets canceled)
        job1.invokeOnCompletion {
            println("Job1 completed")
        }

        // Canceling Job1 that was started, half a second later
        delay(500L) // delay by half a second
        println("Job1 will be canceled")
        job1.cancel()
    }
}