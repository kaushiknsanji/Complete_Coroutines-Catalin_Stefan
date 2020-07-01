package com.kaushiknsanji.coroutinesroom.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.kaushiknsanji.coroutinesroom.R
import com.kaushiknsanji.coroutinesroom.data.local.db.entity.User
import com.kaushiknsanji.coroutinesroom.data.repository.DatabaseRepository
import com.kaushiknsanji.coroutinesroom.utils.common.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [AndroidViewModel] subclass for [com.kaushiknsanji.coroutinesroom.view.MainFragment].
 *
 * @param application [Application] instance required for retrieving Room database instance.
 *
 * @author Kaushik N Sanji
 */
class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    // LiveData for error messages that needs to be shown in Toast
    val error = MutableLiveData<Event<String>>()
    val errorStringId = MutableLiveData<Event<Int>>()

    // LiveData for messages that needs to be shown in Toast
    val messageStringId = MutableLiveData<Event<Int>>()

    // LiveData for launching SignUp Fragment
    val launchSignUp = MutableLiveData<Event<Boolean>>()

    // LiveData for launching Delete Confirmation Dialog
    val launchConfirmDelete = MutableLiveData<Event<Boolean>>()

    // Coroutine Exception Handler to handle uncaught exceptions
    private val databaseExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // Show and log the error
        onError(throwable)
    }

    // Instance of DatabaseRepository to communicate with the database
    private val databaseRepository: DatabaseRepository by lazy { DatabaseRepository(getApplication()) }

    // LiveData for logged-in user information
    val loggedInUser: LiveData<User> = liveData {
        // Emit the current logged-in user if present
        databaseRepository.getCurrentUser()?.let { emit(it) }
    }

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
     * Called when the user clicks on "Log Out" button.
     * Invalidates the current logged-in user in memory and then navigates
     * back to [com.kaushiknsanji.coroutinesroom.view.SignUpFragment]
     */
    fun onLogOut() {
        // Log out the current user, by invalidating the same in memory
        databaseRepository.logOutUser()

        // Show a message on success of Sign out
        messageStringId.postValue(Event(R.string.message_main_sign_out_done))

        // Navigate back to SignUpFragment
        launchSignUp.postValue(Event(true))
    }

    /**
     * Called when the user clicks on "Delete user" button.
     * Triggers an event to launch the Delete Confirmation Dialog.
     */
    fun onDeleteUser() {
        launchConfirmDelete.postValue(Event(true))
    }

    /**
     * Called when the user confirms the Delete action in response to "Delete user" button
     * action.
     */
    fun onConfirmDeleteUser() {
        // Start a Coroutine in IO Dispatcher Threads with the exception handler
        // to perform the deletion of logged-in User
        viewModelScope.launch(Dispatchers.IO + databaseExceptionHandler) {
            if (databaseRepository.deleteCurrentUser()) {
                // When the logged-in user was deleted successfully
                // Publish the success message
                messageStringId.postValue(Event(R.string.message_main_user_delete_done))
                // Navigate back to SignUpFragment
                launchSignUp.postValue(Event(true))
            } else {
                // When the logged-in user information was absent in memory or could not be deleted
                // Show the error message
                errorStringId.postValue(Event(R.string.error_main_user_delete_failure))
            }
        }
    }

    companion object {
        // Constant used for logs
        const val TAG = "MainViewModel"
    }

}
