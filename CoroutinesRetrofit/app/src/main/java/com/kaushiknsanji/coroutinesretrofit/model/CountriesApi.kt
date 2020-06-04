package com.kaushiknsanji.coroutinesretrofit.model

import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API interface for the Countries API.
 *
 * @author Kaushik N Sanji
 */
interface CountriesApi {

    /**
     * API method to get the list of Countries data.
     */
    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<List<Country>>

}