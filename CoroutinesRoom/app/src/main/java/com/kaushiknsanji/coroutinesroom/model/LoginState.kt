package com.kaushiknsanji.coroutinesroom.model

/**
 * Object class to save and retrieve the state of the logged-in User, from anywhere in the app.
 *
 * @author Kaushik N Sanji
 */
object LoginState {

    // Boolean that states if the user is logged in or not
    var isLoggedIn = false

    // Saves the reference to the logged-in user
    var user: User? = null

    /**
     * Called to clear out the logged-in user information, when the user signs-out.
     */
    fun logOut() {
        user = null
        isLoggedIn = false
    }

    /**
     * Called when the user logs-in, to save the information of the logged-in [user].
     */
    fun login(user: User) {
        // Save the user information
        this.user = user
        // Set to true when a user had logged-in
        isLoggedIn = true
    }

}