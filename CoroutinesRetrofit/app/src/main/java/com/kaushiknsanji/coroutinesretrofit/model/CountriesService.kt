package com.kaushiknsanji.coroutinesretrofit.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton Object for managing the Base URL constant of the API, configuring the Retrofit and
 * providing the instance of Retrofit API Service [CountriesApi].
 */
object CountriesService {

    // Base URL of the API
    private val BASE_URL = "https://raw.githubusercontent.com"

    /**
     * Factory method that configures the [Retrofit] and provides an instance
     * of the [CountriesApi].
     *
     * @return Instance of [CountriesApi] API.
     */
    fun getCountriesService(): CountriesApi = Retrofit.Builder()
        .baseUrl(BASE_URL) // URL on which every endpoint will be appended
        // GSON Converter Factory to parse JSON and construct Objects
        .addConverterFactory(GsonConverterFactory.create())
        .build() // Generate the Retrofit instance
        .create(CountriesApi::class.java) // Create the CountriesApi with the Retrofit Configuration

}