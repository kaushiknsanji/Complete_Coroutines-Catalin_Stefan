package com.kaushiknsanji.coroutinesretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaushiknsanji.coroutinesretrofit.model.Country

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

        // Get the countries information
        val dummyData = generateDummyCountries()

        // Update the LiveData
        countries.value = dummyData

        // Clear any loading errors
        countryLoadError.value = ""

        // Stop the [loading] indication
        loading.value = false
    }

    /**
     * Returns Dummy List of country information for testing.
     */
    private fun generateDummyCountries(): List<Country> {
        val countries = arrayListOf<Country>()
        countries.add(Country("dummyCountry1", "dummyCapital1", ""))
        countries.add(Country("dummyCountry2", "dummyCapital2", ""))
        countries.add(Country("dummyCountry3", "dummyCapital3", ""))
        countries.add(Country("dummyCountry4", "dummyCapital4", ""))
        countries.add(Country("dummyCountry5", "dummyCapital5", ""))
        countries.add(Country("dummyCountry1", "dummyCapital1", ""))
        countries.add(Country("dummyCountry2", "dummyCapital2", ""))
        countries.add(Country("dummyCountry3", "dummyCapital3", ""))
        countries.add(Country("dummyCountry4", "dummyCapital4", ""))
        countries.add(Country("dummyCountry5", "dummyCapital5", ""))
        return countries
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

}