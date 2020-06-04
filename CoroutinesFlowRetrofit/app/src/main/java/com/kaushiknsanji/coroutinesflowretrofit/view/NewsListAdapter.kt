package com.kaushiknsanji.coroutinesflowretrofit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaushiknsanji.coroutinesflowretrofit.R
import com.kaushiknsanji.coroutinesflowretrofit.model.NewsArticle
import kotlinx.android.synthetic.main.item_news_article.view.*

/**
 * [RecyclerView.Adapter] subclass for the [RecyclerView] used in [MainActivity]
 * to load the list of News Articles to be displayed.
 *
 * @author Kaushik N Sanji
 */
class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    // Empty list of News Articles to begin with
    private val newsItems = arrayListOf<NewsArticle>()

    /**
     * Called when RecyclerView needs a new [NewsItemViewHolder] of the given type to represent
     * an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new [NewsItemViewHolder] that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_news_article, parent, false)
    )

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount() = newsItems.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [RecyclerView.ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(newsItems[position])
    }

    /**
     * Method that adds new [item] to the list of News Articles shown.
     */
    fun onAddNewsItem(item: NewsArticle) {
        // Adding at the top
        newsItems.add(0, item)
        // Notifying the new item added
        notifyItemInserted(0)
    }

    /**
     * [RecyclerView.ViewHolder] subclass for caching view components of the
     * template item view 'R.layout.item_news_article'.
     */
    class NewsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // References to the views required in the Item View
        private val imageView = view.newsImage
        private val author = view.newsAuthor
        private val title = view.newsTitle
        private val publishedAt = view.newsPublishedAt

        /**
         * Method that binds the views with the [newsItem] data at the position.
         */
        fun bind(newsItem: NewsArticle) {
            // Load the Image onto the ImageView
            imageView.loadImage(newsItem.urlToImage)
            // Bind the author text
            author.text = newsItem.author
            // Bind the title text
            title.text = newsItem.title
            // Bind the published date
            publishedAt.text = newsItem.publishedAt
        }
    }
}