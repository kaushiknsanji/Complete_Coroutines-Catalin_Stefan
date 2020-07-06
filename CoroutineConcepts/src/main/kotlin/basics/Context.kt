package basics

/**
 * Code to access an element of Coroutine's Context.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Start a Coroutine by providing a Name for it. This name is an element of CoroutineContext
        launch(CoroutineName("myCoroutine")) {
            // Printing the Name of the Coroutine by accessing the corresponding key via its Context
            println("This is run from ${coroutineContext[CoroutineName.Key]}")
        }
    }
}