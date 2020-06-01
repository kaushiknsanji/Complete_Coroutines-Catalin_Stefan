package com.kaushiknsanji.imageprocessingcoroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

/**
 * Main activity of the app that inflates the layout 'R.layout.activity_main'
 * to show the downloaded image with grey filter applied.
 */
class MainActivity : AppCompatActivity() {

    // URL of the image to be downloaded
    private val IMAGE_URL =
        "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"

    // Coroutine Scope with Main Dispatcher for working with UI
    private val uiCoroutineScope = CoroutineScope(Dispatchers.Main)

    /**
     * Called when the activity is starting.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start a Coroutine to download, process and set the image
        uiCoroutineScope.launch {
            // Start a Coroutine to get the Deferred result of the downloaded Bitmap image
            val originalBitmapDeferred =
                uiCoroutineScope.async(Dispatchers.IO) { getOriginalBitmap() }
            // Wait for the Bitmap to be downloaded
            val downloadedBitmap = originalBitmapDeferred.await()
            // Set the Bitmap onto the ImageView
            loadImage(downloadedBitmap)
        }
    }

    /**
     * Returns the [Bitmap] downloaded for the [IMAGE_URL]
     */
    private fun getOriginalBitmap(): Bitmap = URL(IMAGE_URL).openStream().use {
        BitmapFactory.decodeStream(it)
    }

    /**
     * Sets the downloaded [bitmap][Bitmap] onto the ImageView.
     */
    private fun loadImage(bitmap: Bitmap) {
        // Hide the Progress spinner
        progressBar.visibility = View.GONE
        // Set the Image on the ImageView
        imageView.setImageBitmap(bitmap)
        // Show the ImageView
        imageView.visibility = View.VISIBLE
    }
}
