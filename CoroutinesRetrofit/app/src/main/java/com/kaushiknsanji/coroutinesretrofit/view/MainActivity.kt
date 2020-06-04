package com.kaushiknsanji.coroutinesretrofit.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaushiknsanji.coroutinesretrofit.R
import com.kaushiknsanji.coroutinesretrofit.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity of the App that inflates the layout 'R.layout.activity_main'.
 *
 * @author Kaushik N Sanji
 */
class MainActivity : AppCompatActivity() {

    // Instance of the Activity's ViewModel
    lateinit var viewModel: ListViewModel

    // Instance of the RecyclerView Adapter initialized with empty data
    private val countriesAdapter = CountryListAdapter(arrayListOf())

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
        // Refresh/reload the list of countries
        viewModel.refresh()

        // Initialize RecyclerView with its Layout Manager and Adapter
        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        // Setup ViewModel's LiveData Observers
        observeViewModel()
    }

    /**
     * Initializes [androidx.lifecycle.LiveData] observers.
     */
    fun observeViewModel() {

        // Register an observer on the List of Countries to reload the adapter with the new content
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                // Make the RecyclerView visible
                countriesList.visibility = View.VISIBLE
                // Update the adapter with new data
                countriesAdapter.updateCountries(it)
            }
        })

        // Register an observer on the errors to display an error if present
        viewModel.countryLoadError.observe(this, Observer { isError ->
            list_error.visibility = if (isError == "") View.GONE else View.VISIBLE
        })

        // Register an observer on the loading progress to show/hide the download indicator
        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE

                // While loading, hide the error TextView and the RecyclerView
                if (it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }
        })
    }

}