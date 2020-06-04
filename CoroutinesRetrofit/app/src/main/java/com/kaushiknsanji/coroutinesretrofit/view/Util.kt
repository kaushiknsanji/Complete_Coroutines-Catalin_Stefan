package com.kaushiknsanji.coroutinesretrofit.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kaushiknsanji.coroutinesretrofit.R

/**
 * Extension function on [ImageView] to load an image from the given [uri].
 */
fun ImageView.loadImage(uri: String?) {
    // Glide customization to show an image on loading error
    val options = RequestOptions().error(R.mipmap.ic_launcher_round)
    // Download and load image into the ImageView using Glide instance
    Glide.with(this)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}