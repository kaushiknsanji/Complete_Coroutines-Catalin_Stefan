package basics

/**
 * Code to show how to handle the exception thrown in a Coroutine started with 'async' builder.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Start a Coroutine and save its Deferred Job instance
        val deferredJob = GlobalScope.async {
            println("Throwing exception from job")
            // Throw some dummy exception to test
            throw IllegalStateException("Illegal Exception at async")
        }

        // Handling exception with try-catch
        try {
            // Wait for the deferred value, so that the exception gets thrown
            deferredJob.await()
        } catch (e: IllegalStateException) {
            println("Caught IllegalStateException: ${e.localizedMessage}")
        }
    }
}