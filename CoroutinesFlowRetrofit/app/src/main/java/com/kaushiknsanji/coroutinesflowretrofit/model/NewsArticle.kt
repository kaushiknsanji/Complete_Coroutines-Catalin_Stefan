package com.kaushiknsanji.coroutinesflowretrofit.model

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
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null
)