package basics

/**
 * Code to demonstrate how to use different dispatchers and how they assign a thread for the Coroutine.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.*

fun main() {
    // Start a Coroutine Scope in Thread blocking mode
    runBlocking {
        // Start a Coroutine in Main Dispatcher
        //launch(Dispatchers.Main) {
        //    println("Main dispatcher: Thread: ${Thread.currentThread().name}")
        //}

        // Start a Coroutine in Unconfined Dispatcher
        launch(Dispatchers.Unconfined) {
            // The Thread chosen here will be inherited. So, this will be the Main thread
            println("Unconfined dispatcher1: Thread: ${Thread.currentThread().name}")
            delay(100L) // pause for 100ms
            // After 100ms, the Thread would have switched here since this dispatcher is not confined to any Thread
            println("Unconfined dispatcher2: Thread: ${Thread.currentThread().name}")
        }

        // Start a Coroutine in Default Dispatcher
        launch(Dispatchers.Default) {
            println("Default dispatcher: Thread: ${Thread.currentThread().name}")
        }

        // Start a Coroutine in IO Dispatcher
        launch(Dispatchers.IO) {
            println("IO dispatcher: Thread: ${Thread.currentThread().name}")
        }

        // Start a Coroutine with a new single-thread executor dispatcher
        launch(newSingleThreadContext("MyThread")) {
            println("newSingleThreadContext dispatcher: Thread: ${Thread.currentThread().name}")
        }
    }
}