package basics

/**
 * Simple "Hello, World!" code using Coroutines.
 *
 * @author Kaushik N Sanji
 */

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    // Launch a Coroutine with Global Scope
    GlobalScope.launch {
        delay(1000) // Delay the execution by a second
        println("World!") // Print "World!"
    }

    print("Hello, ") // Print "Hello, "
    Thread.sleep(2000) // Wait here for 2 seconds
}