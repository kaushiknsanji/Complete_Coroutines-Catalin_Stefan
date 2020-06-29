package com.kaushiknsanji.coroutinesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kaushiknsanji.coroutinesroom.utils.common.Event

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

    val loginComplete = MutableLiveData<Event<Boolean>>()
    val error = MutableLiveData<String>()

    // LiveData for launching Main Fragment
    val launchMain = MutableLiveData<Event<Boolean>>()

    // LiveData for launching SignUp Fragment
    val launchSignUp = MutableLiveData<Event<Boolean>>()

    fun login(username: String, password: String) {

    }

    /**
     * Called when the user clicks on the "Login" Button to login with the credentials.
     */
    fun onLogin() {
        launchMain.postValue(Event(true))
    }

    /**
     * Called when the user clicks on the "Go To SignUp" Button.
     * Triggers an event to launch the [com.kaushiknsanji.coroutinesroom.view.SignUpFragment]
     */
    fun onGoToSignUp() {
        launchSignUp.postValue(Event(true))
    }

}
