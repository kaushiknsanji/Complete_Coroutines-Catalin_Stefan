package com.kaushiknsanji.coroutinesflowretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * [ViewModel] subclass for [com.kaushiknsanji.coroutinesflowretrofit.view.MainActivity].
 *
 * @author Kaushik N Sanji
 */
class ListViewModel : ViewModel() {
    // LiveData for the new News-Article
    val newsArticle = MutableLiveData<String>()
}