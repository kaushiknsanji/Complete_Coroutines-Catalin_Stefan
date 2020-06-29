package com.kaushiknsanji.coroutinesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kaushiknsanji.coroutinesroom.utils.common.Event

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

    val userDeleted = MutableLiveData<Boolean>()
    val signOut = MutableLiveData<Boolean>()

    // LiveData for launching SignUp Fragment
    val launchSignUp = MutableLiveData<Event<Boolean>>()

    /**
     * Called when the user clicks on "Log Out" button.
     */
    fun onLogOut() {
        launchSignUp.postValue(Event(true))
    }

    /**
     * Called when the user clicks on "Delete user" button.
     */
    fun onDeleteUser() {
        launchSignUp.postValue(Event(true))
    }

}
