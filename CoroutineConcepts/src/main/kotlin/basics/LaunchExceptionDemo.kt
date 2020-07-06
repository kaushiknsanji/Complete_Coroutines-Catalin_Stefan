package basics

/**
 * Code to show how an exception is thrown in a Coroutine started with 'launch' builder.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Start a Coroutine and save its Job instance
        val job = GlobalScope.launch {
            println("Throwing exception from job")
            // Throw some dummy exception to test
            throw IllegalStateException("Illegal Exception at launch")
        }

        // Trying to join the Coroutine to the Main thread, so that the exception gets thrown
        job.join()
    }
}