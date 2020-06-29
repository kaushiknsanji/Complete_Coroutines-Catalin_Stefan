package com.kaushiknsanji.coroutinesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kaushiknsanji.coroutinesroom.utils.common.Event

/**
 * [AndroidViewModel] subclass for [com.kaushiknsanji.coroutinesroom.view.SignUpFragment].
 *
 * @param application [Application] instance required for retrieving Room database instance.
 *
 * @author Kaushik N Sanji
 */
class SignUpViewModel(
    application: Application
) : AndroidViewModel(application) {

    val signUpComplete = MutableLiveData<Event<Boolean>>()
    val error = MutableLiveData<String>()

    // LiveData for launching Main Fragment
    val launchMain = MutableLiveData<Event<Boolean>>()

    // LiveData for launching Login Fragment
    val launchLogin = MutableLiveData<Event<Boolean>>()

    /**
     * Called when the user clicks on "Sign Up" Button after entering the required credentials.
     */
    fun onSignUp(username: String, password: String, info: String) {
        launchMain.postValue(Event(true))
    }

    /**
     * Called when the user clicks on the "Go To Login" Button.
     * Triggers an event to launch the [com.kaushiknsanji.coroutinesroom.view.LoginFragment]
     */
    fun onGoToLogin() {
        launchLogin.postValue(Event(true))
    }

}
