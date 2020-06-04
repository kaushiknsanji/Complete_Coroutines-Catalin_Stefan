package com.kaushiknsanji.coroutinesflowretrofit.model

import retrofit2.http.GET

/**
 * Retrofit API interface for the NewsArticles API.
 *
 * @author Kaushik N Sanji
 */
interface NewsService {

    /**
     * API method to get a list of News Articles.
     */
    @GET("news.json")
    suspend fun getNews(): List<NewsArticle>

}