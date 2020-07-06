package basics

/**
 * Code to show how Suspend functions can access and modify any variables
 * in a seamless way that looks sequential.
 *
 * @author Kaushik N Sanji
 */
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Tracks the number of calls to suspend functions
var functionCalls = 0

fun main() {
    // Start a Coroutine in Global Scope to call basics.completeMessage
    GlobalScope.launch { completeMessage() }
    // Start a Coroutine in Global Scope to call basics.improveMessage
    GlobalScope.launch { improveMessage() }
    print("Hello, ")
    Thread.sleep(2000L) // Wait for 2 seconds for the Coroutines to complete
    // Print the number of calls made
    println("There have been $functionCalls calls so far")
}

/**
 * Suspend function that prints "World!"
 */
suspend fun completeMessage() {
    delay(500L) // Delay by half a second
    println("World!")
    // Increment the call count
    functionCalls++
}

/**
 * Suspend function that prints "Suspend functions are cool" in a new line
 */
suspend fun improveMessage() {
    delay(1000L) // Delay by a second
    println("Suspend functions are cool")
    // Increment the call count
    functionCalls++
}