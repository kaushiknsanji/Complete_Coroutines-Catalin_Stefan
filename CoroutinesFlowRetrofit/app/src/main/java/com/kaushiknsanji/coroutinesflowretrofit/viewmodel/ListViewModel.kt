package com.kaushiknsanji.coroutinesflowretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.kaushiknsanji.coroutinesflowretrofit.model.NewsArticle
import com.kaushiknsanji.coroutinesflowretrofit.model.NewsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

/**
 * [ViewModel] subclass for [com.kaushiknsanji.coroutinesflowretrofit.view.MainActivity].
 *
 * @author Kaushik N Sanji
 */
class ListViewModel : ViewModel() {
    // Coroutine Exception Handler
    private val networkExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // Log the error
        onError(throwable)
    }

    /**
     * Called when there is an error in the Coroutine process used for downloading the articles.
     *
     * @param throwable [Throwable] instance of the error thrown
     */
    private fun onError(throwable: Throwable?) {
        // Log the error
        Log.e(TAG, "${throwable?.localizedMessage}", throwable)
    }

    // LiveData for the new News-Article, loaded from a Coroutine Flow in IO Dispatcher Threads
    // with uncaught exception handler
    val newsArticle =
        NewsRepository().getNewsArticles().asLiveData(Dispatchers.IO + networkExceptionHandler)

    // List to save the News Articles currently loaded
    private val newsArticlesList = mutableListOf<NewsArticle>()

    init {
        // Called when this ViewModel is first initialized

        // Register an observer forever on the New News-Article to save-off the articles already loaded
        newsArticle.observeForever { newsArticle: NewsArticle ->
            // Save in the same order, i.e., new article at the top
            newsArticlesList.add(0, newsArticle)
        }
    }

    // LiveData for preloading the adapter with the list of News Articles previously downloaded
    private val _newsArticlesPreLoad: MutableLiveData<List<NewsArticle>> = MutableLiveData()
    val newsArticlesPreLoad: LiveData<List<NewsArticle>> =
        _newsArticlesPreLoad.map { list: List<NewsArticle>? ->
            list?.let { list } ?: mutableListOf()
        }

    /**
     * Called when this ViewModel's Activity is created.
     */
    fun onCreate() {
        // Publish the previously downloaded list of News Articles to preload the adapter
        _newsArticlesPreLoad.postValue(newsArticlesList)
    }

    companion object {
        // Constant used for logs
        const val TAG = "ListViewModel"
    }

}