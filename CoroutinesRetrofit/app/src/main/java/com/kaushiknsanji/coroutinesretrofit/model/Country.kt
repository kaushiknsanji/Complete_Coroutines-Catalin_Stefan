package com.kaushiknsanji.coroutinesretrofit.model

/**
 * Model class for Country information
 *
 * @property countryName Name of the Country. Can be `null`.
 * @property capital Country's Capital. Can be `null`.
 * @property flag URI String to the Country's Flag Image. Can be `null`.
 * @constructor Creates and returns an instance of [Country].
 *
 * @author Kaushik N Sanji
 */
data class Country(
    val countryName: String?,
    val capital: String?,
    val flag: String?
)