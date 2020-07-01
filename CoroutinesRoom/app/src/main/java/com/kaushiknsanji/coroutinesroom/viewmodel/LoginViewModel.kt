package com.kaushiknsanji.coroutinesroom.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaushiknsanji.coroutinesroom.R
import com.kaushiknsanji.coroutinesroom.data.repository.DatabaseRepository
import com.kaushiknsanji.coroutinesroom.utils.common.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [AndroidViewModel] subclass for [com.kaushiknsanji.coroutinesroom.view.LoginFragment].
 *
 * @param application [Application] instance required for retrieving Room database instance.
 *
 * @author Kaushik N Sanji
 */
class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {

    // LiveData for error messages that needs to be shown in Toast
    val error = MutableLiveData<Event<String>>()
    val errorStringId = MutableLiveData<Event<Int>>()

    // LiveData for messages that needs to be shown in Toast
    val messageStringId = MutableLiveData<Event<Int>>()

    // LiveData for launching Main Fragment
    val launchMain = MutableLiveData<Event<Boolean>>()

    // LiveData for launching SignUp Fragment
    val launchSignUp = MutableLiveData<Event<Boolean>>()

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
     * Called when the user clicks on the "Login" Button to login with the entered credentials.
     * Executes the Login process in a coroutine after doing the required validations.
     *
     * @param username [String] value of the username captured from the EditText.
     * @param password [String] value of the password captured from the EditText.
     */
    fun onLogin(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            // When one of the required fields are empty, show a message to the user
            // requesting to fill all the fields
            messageStringId.postValue(Event(R.string.message_login_empty_fields))
        } else {
            // When the required fields are present, start the Login Process

            // Start a Coroutine in IO Dispatcher Threads with the exception handler to execute
            // the Login in the background
            viewModelScope.launch(Dispatchers.IO + databaseExceptionHandler) {
                // Do the Authentication and Login
                val authPair = databaseRepository.authenticateUser(username, password)
                if (authPair.first) {
                    // When the User is authenticated successfully for the entered credentials

                    // Publish the corresponding success message
                    messageStringId.postValue(Event(authPair.second))
                    // Navigate to Main Fragment
                    launchMain.postValue(Event(true))
                } else {
                    // When the User authentication failed due to incorrect password
                    // or non-existing user for the entered username

                    // Show the corresponding error message
                    errorStringId.postValue(Event(authPair.second))
                }
            }
        }
    }

    /**
     * Called when the user clicks on the "Go To SignUp" Button.
     * Triggers an event to launch the [com.kaushiknsanji.coroutinesroom.view.SignUpFragment]
     */
    fun onGoToSignUp() {
        // Navigate back to SignUpFragment
        launchSignUp.postValue(Event(true))
    }

    companion object {
        // Constant used for logs
        const val TAG = "LoginViewModel"
    }

}
