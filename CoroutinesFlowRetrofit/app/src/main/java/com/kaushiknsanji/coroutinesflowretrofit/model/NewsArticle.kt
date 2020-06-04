package com.kaushiknsanji.coroutinesflowretrofit.model

import com.google.gson.annotations.SerializedName

/**
 * Data class of News Articles, embedded in the Remote API Responses.
 *
 * @property author Author of the [NewsArticle].
 * @property title Title of the [NewsArticle].
 * @property description News description of the [NewsArticle].
 * @property url URL to the [NewsArticle].
 * @property urlToImage URL to the banner image of the [NewsArticle].
 * @property publishedAt String representation of the published date of the [NewsArticle].
 * @constructor Creates and returns an instance of [NewsArticle].
 *
 * @author Kaushik N Sanji
 */
data class NewsArticle(
    @SerializedName("author")
    val author: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("imageUrl")
    val urlToImage: String? = null,

    @SerializedName("publishedAt")
    val publishedAt: String? = null
)