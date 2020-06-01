package com.kaushiknsanji.imageprocessingcoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Main activity of the app that inflates the layout 'R.layout.activity_main'
 * to show the downloaded image with grey filter applied.
 */
class MainActivity : AppCompatActivity() {

    // URL of the image to be downloaded
    private val IMAGE_URL =
        "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"

    /**
     * Called when the activity is starting.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
