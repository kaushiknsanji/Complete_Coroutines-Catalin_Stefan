package com.kaushiknsanji.coroutinesroom.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaushiknsanji.coroutinesroom.R
import com.kaushiknsanji.coroutinesroom.data.local.db.entity.User
import com.kaushiknsanji.coroutinesroom.data.repository.DatabaseRepository
import com.kaushiknsanji.coroutinesroom.utils.common.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [AndroidViewModel] subclass for [com.kaushiknsanji.coroutinesroom.view.SignUpFragment].
 *
 * @param application [Application] instance required for retrieving [DatabaseRepository] instance.
 *
 * @author Kaushik N Sanji
 */
class SignUpViewModel(
    application: Application
) : AndroidViewModel(application) {

    // LiveData for error messages that needs to be shown in Toast
    val error = MutableLiveData<Event<String>>()
    val errorStringId = MutableLiveData<Event<Int>>()

    // LiveData for messages that needs to be shown in Toast
    val messageStringId = MutableLiveData<Event<Int>>()

    // LiveData for launching Main Fragment
    val launchMain = MutableLiveData<Event<Boolean>>()

    // LiveData for launching Login Fragment
    val launchLogin = MutableLiveData<Event<Boolean>>()

    // Coroutine Exception Handler to handle uncaught exceptions
    private val databaseExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // Show and log the error
        onError(throwable)
    }

    // Instance of DatabaseRepository to communicate with the database
    private val databaseRepository: DatabaseRepository by lazy { DatabaseRepository(getApplication()) }

    /**
     * Called when there is an error in the Coroutine process while communicating with the database.
     *
     * @param throwable [Throwable] instance of the error thrown.
     */
    private fun onError(throwable: Throwable?) {
        // Log the error
        Log.e(TAG, "${throwable?.localizedMessage}", throwable)
        // Show the error
        error.postValue(Event("${throwable?.localizedMessage}"))
    }

    /**
     * Called when the user clicks on "Sign Up" Button after entering the required credentials.
     * Executes the Sign Up process in a coroutine after doing the required validations.
     *
     * @param username [String] value of the username captured from the EditText.
     * @param password [String] value of the password captured from the EditText.
     * @param info [String] value of the info captured from the EditText.
     */
    fun onSignUp(username: String, password: String, info: String) {
        if (username.isBlank() || password.isBlank() || info.isBlank()) {
            // When one of the required fields are empty, show a message to the user
            // requesting to fill all the fields
            messageStringId.postValue(Event(R.string.message_sign_up_empty_fields))
        } else {
            // When the required fields are present, start the Sign Up Process

            // Start a Coroutine in IO Dispatcher Threads with the exception handler to execute
            // the sign-up in the background
            viewModelScope.launch(Dispatchers.IO + databaseExceptionHandler) {
                // Check if there is an existing user with the same username
                val existingUser: User? = databaseRepository.getUserByName(username)
                if (existingUser != null) {
                    // When there is a User with the same username
                    // Show the error message to indicate that the given username is already taken
                    errorStringId.postValue(Event(R.string.error_sign_up_username_exists))
                } else {
                    // When the given username is unique
                    // Sign Up the user with the given credentials
                    databaseRepository.signUpUser(username, password, info)
                        .takeIf { it > 0 } // Id is greater than 0 when the entry is made successfully
                        ?.let {
                            // On SignUp Complete
                            // Publish the success message
                            messageStringId.postValue(Event(R.string.message_sign_up_done))
                            // Navigate to Main Fragment
                            launchMain.postValue(Event(true))
                        }
                }
            }
        }
    }

    /**
     * Called when the user clicks on the "Go To Login" Button.
     * Triggers an event to launch the [com.kaushiknsanji.coroutinesroom.view.LoginFragment]
     */
    fun onGoToLogin() {
        launchLogin.postValue(Event(true))
    }

    companion object {
        // Constant used for logs
        const val TAG = "SignUpViewModel"
    }

}
