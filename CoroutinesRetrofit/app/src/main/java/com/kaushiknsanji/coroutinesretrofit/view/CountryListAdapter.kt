package com.kaushiknsanji.coroutinesretrofit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaushiknsanji.coroutinesretrofit.R
import com.kaushiknsanji.coroutinesretrofit.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

/**
 * [RecyclerView.Adapter] subclass for the [RecyclerView] used in [MainActivity]
 * to load the list of [countries] to be displayed.
 *
 * @property countries List of countries source managed by this adapter.
 *
 * @author Kaushik N Sanji
 */
class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    /**
     * Called when RecyclerView needs a new [CountryViewHolder] of the given type to represent
     * an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new [CountryViewHolder] that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount() = countries.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [RecyclerView.ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    /**
     * Method that clears the adapter and loads [newCountries].
     */
    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    /**
     * [RecyclerView.ViewHolder] subclass for caching view components of the
     * template item view 'R.layout.item_country'
     */
    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // References to the views required in the Item View
        private val imageView = view.imageView
        private val countryName = view.name
        private val countryCapital = view.capital

        /**
         * Method that binds the views with the [country] data at the position.
         */
        fun bind(country: Country) {
            // Bind the Country Name
            countryName.text = country.countryName
            // Bind the Country Capital
            countryCapital.text = country.capital
            // Load the Image onto the ImageView
            imageView.loadImage(country.flag)
        }
    }
}