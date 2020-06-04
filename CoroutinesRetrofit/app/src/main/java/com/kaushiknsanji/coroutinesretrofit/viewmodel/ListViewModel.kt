package com.kaushiknsanji.coroutinesretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaushiknsanji.coroutinesretrofit.model.CountriesService
import com.kaushiknsanji.coroutinesretrofit.model.Country
import kotlinx.coroutines.*

/**
 * [ViewModel] subclass for [com.kaushiknsanji.coroutinesretrofit.view.MainActivity]
 *
 * @author Kaushik N Sanji
 */
class ListViewModel : ViewModel() {

    // LiveData for the list of Countries
    val countries = MutableLiveData<List<Country>>()

    // LiveData for loading errors
    val countryLoadError = MutableLiveData<String?>()

    // LiveData for data loading progress indication
    val loading = MutableLiveData<Boolean>()

    // Retrofit API for the Countries API service
    private val countriesService = CountriesService.getCountriesService()

    // Coroutine Job instance to download the data from the API
    private var job: Job? = null

    // Exception Handler for the Coroutine
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    /**
     * Called when Activity is created, to reload the content
     */
    fun refresh() {
        fetchCountries()
    }

    /**
     * Method that starts downloading of countries information and updates the [countries] LiveData.
     */
    private fun fetchCountries() {
        // Start the [loading] indication
        loading.value = true

        // Start a Coroutine Scope in the UI Dispatcher, with custom exception handler and save its job
        job = CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
            // Get the countries information from the API on the IO Dispatcher
            val response = withContext(Dispatchers.IO) {
                countriesService.getCountries()
            }

            // Back to the UI thread

            // Check the response
            if (response.isSuccessful) {
                // When we have the response

                // Update the LiveData
                countries.value = response.body()
                // Clear any loading errors
                countryLoadError.value = ""
                // Stop the [loading] indication
                loading.value = false
            } else {
                // When we have no response but an error
                // Show the error message
                onError("Error: ${response.message()}")
            }
        }

    }

    /**
     * Called when an error [message] needs to displayed for the error that would have occurred
     * while loading country list information.
     */
    private fun onError(message: String) {
        // Update the LiveData with the error message
        countryLoadError.value = message
        // Stop the [loading] indication
        loading.value = false
    }

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     *
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    override fun onCleared() {
        super.onCleared()
        // Clear any active Coroutine jobs
        job?.cancel()
    }

}