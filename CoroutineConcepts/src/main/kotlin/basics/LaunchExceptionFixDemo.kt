package basics

/**
 * Code to show how to handle the exception thrown in a Coroutine started with 'launch' builder.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Handler to handle the exception thrown
        val myHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            // Prints the exception handled
            println("Exception handled: ${throwable.localizedMessage}")
        }

        // Start a Coroutine with the above Exception Handler and save its Job instance
        val job = GlobalScope.launch(myHandler) {
            println("Throwing exception from job")
            // Throw some dummy exception to test
            throw IllegalStateException("Illegal Exception at launch")
        }

        // Trying to join the Coroutine to the Main thread, so that the exception gets thrown
        job.join()
    }
}