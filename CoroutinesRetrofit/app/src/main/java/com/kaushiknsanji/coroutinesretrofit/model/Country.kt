package com.kaushiknsanji.coroutinesretrofit.model

import com.google.gson.annotations.SerializedName

/**
 * Data class of Country information, embedded in the Remote API Responses.
 *
 * @property countryName Name of the Country. Can be `null`.
 * @property capital Country's Capital. Can be `null`.
 * @property flag URI String to the Country's Flag Image. Can be `null`.
 * @constructor Creates and returns an instance of [Country].
 *
 * @author Kaushik N Sanji
 */
data class Country(
    @SerializedName("name")
    val countryName: String?,

    @SerializedName("capital")
    val capital: String?,

    @SerializedName("flagPNG")
    val flag: String?
)