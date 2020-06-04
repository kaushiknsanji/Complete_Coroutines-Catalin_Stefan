package com.kaushiknsanji.coroutinesflowretrofit.model

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repository for the News Articles API that configures the [Retrofit] and provides
 * the instance of the [NewsService] API, along with establishing a [Flow] for emitting
 * each News Articles one-by-one with a delay.
 *
 * @author Kaushik N Sanji
 */
class NewsRepository {

    companion object {
        // Constant for the Base Url of the API
        private const val BASE_URL = "https://raw.githubusercontent.com/DevTides/NewsApi/master"

        // Constant that signifies how often to publish a News Article to the client
        private const val NEWS_DELAY = 3000L
    }

    // Instance of NewsService API
    private val newsService = Retrofit.Builder()
        .baseUrl(BASE_URL) // URL on which every endpoint will be appended
        // GSON Converter Factory to parse JSON and construct Objects
        .addConverterFactory(GsonConverterFactory.create())
        .build() // Generate the Retrofit instance
        .create(NewsService::class.java) // Create the NewsService API with the Retrofit Configuration

    /**
     * Creates and returns a [Flow] of the News Articles from the [NewsService] API, where
     * each article is emitted with a delay of [NEWS_DELAY].
     */
    fun getNewsArticles(): Flow<NewsArticle> = flow {
        // Get the News Articles from the API
        val newsArticles = newsService.getNews()
        newsArticles.onEach { newsArticle: NewsArticle ->
            // For each News Article
            // Emit the same
            emit(newsArticle)
            // Pause before emitting the next Article
            delay(NEWS_DELAY)
        }
    }

}