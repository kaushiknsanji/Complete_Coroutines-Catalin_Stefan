package com.kaushiknsanji.coroutinesroom.data.repository

import android.content.Context
import com.kaushiknsanji.coroutinesroom.R
import com.kaushiknsanji.coroutinesroom.data.local.db.UserDatabase
import com.kaushiknsanji.coroutinesroom.data.local.db.entity.User
import com.kaushiknsanji.coroutinesroom.data.model.LoginState

/**
 * Repository for Database management.
 *
 * @property userDatabase Private [UserDatabase] instance provided by 'Invoke' Operator function.
 * @constructor Instance of [DatabaseRepository] created and provided by 'Invoke' Operator function.
 * Default constructor is private and does not provide any instance.
 *
 * @author Kaushik N Sanji
 */
class DatabaseRepository private constructor(private val userDatabase: UserDatabase) {

    companion object {
        // Singleton instance of this Repository
        @Volatile
        private var instance: DatabaseRepository? = null

        // Lock object for synchronization during instance retrieval/creation
        private val LOCK = Any()

        /**
         * 'Invoke' Operator function to create and provide the singleton instance of [DatabaseRepository].
         * Instance is created only when [instance] is not initialized yet.
         *
         * @param context [Context] to create the [UserDatabase] instance for.
         */
        operator fun invoke(context: Context): DatabaseRepository = instance ?: synchronized(LOCK) {
            instance ?: DatabaseRepository(UserDatabase(context)).also {
                // Save the instance built
                instance = it
            }
        }
    }

    /**
     * Inserts a [user] into "users" table.
     *
     * @return [Long] value of the id of the [user] inserted/replaced.
     */
    private suspend fun insertUser(user: User): Long = userDatabase.userDao().insertUser(user)

    /**
     * Retrieves [User] data identified by the given [username] from the "users" table.
     *
     * @return [User] instance of the identified user if any; `null` otherwise.
     */
    suspend fun getUserByName(username: String): User? =
        userDatabase.userDao().getUserByName(username)

    /**
     * Deletes a user record identified by the given [id] from the "users" table.
     *
     * @return [Int] value of the number of users deleted successfully.
     */
    private suspend fun deleteUserById(id: Long): Int = userDatabase.userDao().deleteUserById(id)

    /**
     * Performs user sign-up and saves the created [User] information in the database.
     *
     * @param username [String] value of the username entered by the user.
     * @param password [String] value of the password entered by the user.
     * @param info [String] value of the info provided by the user.
     *
     * @return [Long] value of the id of the [User] inserted/replaced.
     */
    suspend fun signUpUser(username: String, password: String, info: String): Long =
        User(username, password.hashCode(), info).run {
            // With the created User for the given data, insert into the database and return its user-id
            return insertUser(this).also { id: Long ->
                // Save the Id generated, in the same User object
                this.id = id
                // Save this logged-in user information (in memory)
                saveNewUser(this)
            }
        }

    /**
     * Retrieves the logged-in [User] information if the user had logged-in; otherwise returns `null`.
     */
    fun getCurrentUser(): User? = LoginState.user

    /**
     * Logs out the currently logged-in [User].
     */
    fun logOutUser() = LoginState.logOut()

    /**
     * Saves the [user] information of the new logged-in [User].
     */
    private fun saveNewUser(user: User): Unit = LoginState.login(user)

    /**
     * Performs delete operation of the logged-in [User] from the the "users" table.
     *
     * @return `true` if the user was present in memory and deleted successfully; `false` otherwise.
     */
    suspend fun deleteCurrentUser(): Boolean =
        getCurrentUser()?.let { user: User ->
            // Delete the active user if any
            deleteUserById(user.id).takeIf { it > 0 }?.let {
                // When deleted successfully, invalidate the user in memory and return true
                logOutUser()
                true
            } ?: false // Returning false when delete was unsuccessful
        } ?: false // Returning false when there was no user in memory to delete

    /**
     * Performs Authentication and Login of a [User] for the entered [username] and [password].
     *
     * @return Returns a [Pair] containing an Authentication state [Boolean] and an [Int]
     * pointing to the string resource of a message to be shown. Authentication state is `true`
     * if there is a [User] with the same [username], and the entered [password]
     * matches; `false` otherwise.
     */
    suspend fun authenticateUser(username: String, password: String): Pair<Boolean, Int> =
        getUserByName(username)?.let { user: User ->
            // When there is an existing User with the same username
            // Try and authenticate the user with the password entered
            (user.passwordHash == password.hashCode()).takeIf { it }?.let {
                // When user entered password is correct

                // Save this logged-in user information (in memory)
                saveNewUser(user)
                // Returning with true when the password matches
                (true to R.string.message_login_done)
            }
                ?: (false to R.string.error_login_password_invalid) // Returning with false when the password is incorrect
        }
            ?: (false to R.string.error_login_username_invalid) // Returning with false when there is no User for the given username
}