package com.kaushiknsanji.coroutinesflowretrofit.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaushiknsanji.coroutinesflowretrofit.R
import com.kaushiknsanji.coroutinesflowretrofit.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity of the App that inflates the layout 'R.layout.activity_main'.
 *
 * @author Kaushik N Sanji
 */
class MainActivity : AppCompatActivity() {

    // Instance of the Activity's ViewModel
    lateinit var viewModel: ListViewModel

    // Instance of the RecyclerView Adapter
    private val newsListAdapter = NewsListAdapter()

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState. Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the Activity's ViewModel instance
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        // Initialize RecyclerView with its Layout Manager and Adapter
        newsList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsListAdapter
        }

        // Setup ViewModel's LiveData Observers
        setupObservers()
    }

    /**
     * Method that initializes the [androidx.lifecycle.LiveData] observers.
     */
    private fun setupObservers() {
        // Register an observer on the New News-Article to load the adapter with the same
        viewModel.newsArticle.observe(this, Observer { article ->
            // Hide the loading spinner
            loading_view.visibility = View.GONE
            // Show the RecyclerView
            newsList.visibility = View.VISIBLE
            // Load this latest Article
            newsListAdapter.onAddNewsItem(article)
            // Scroll to top since the latest gets loaded at the top
            newsList.smoothScrollToPosition(0)
        })
    }
}