package com.kaushiknsanji.coroutinesroom.data.model

import com.kaushiknsanji.coroutinesroom.data.local.db.entity.User

/**
 * Singleton class to save and retrieve the state of the logged-in User.
 *
 * @author Kaushik N Sanji
 */
object LoginState {

    // Boolean that states if the user is logged in or not
    private var isLoggedIn = false

    // Saves the reference to the logged-in user
    var user: User? = null
        private set

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
        LoginState.user = user
        // Set to true when a user had logged-in
        isLoggedIn = true
    }

}