package com.kaushiknsanji.coroutinesflowretrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kaushiknsanji.coroutinesflowretrofit.model.NewsRepository

/**
 * [ViewModel] subclass for [com.kaushiknsanji.coroutinesflowretrofit.view.MainActivity].
 *
 * @author Kaushik N Sanji
 */
class ListViewModel : ViewModel() {
    // LiveData for the new News-Article, loaded from a Coroutine Flow
    val newsArticle = NewsRepository().getNewsArticles().asLiveData()
}