/**
 * All Kotlin Extensions used in the project
 *
 * @author Kaushik N Sanji
 */
package com.kaushiknsanji.imageprocessingcoroutines

import android.graphics.Bitmap
import android.graphics.Color

/**
 * Extension function on [Bitmap] that applies grey filer and returns a new Bitmap for the same.
 */
fun Bitmap.greyFilter(): Bitmap {
    // Create an integer array for the number of pixels
    val pixels = IntArray(width * height)

    // Read pixel information into the integer array from source
    getPixels(pixels, 0, width, 0, 0, width, height)

    (0 until height).forEach { y ->
        (0 until width).forEach { x ->
            // Get current index in 2D Matrix
            val index = y * width + x
            pixels[index] = pixels[index].run {
                // Grey color value is derived from 50% of each of the three primary colors at the index
                ((Color.red(this) + Color.green(this) + Color.blue(this)) / 3).run {
                    // Using the computed Grey color value, return its rgb value
                    Color.rgb(this, this, this)
                }
            }
        }
    }

    // Create and return a new bitmap for the computed grey image from source
    return Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565).apply {
        setPixels(pixels, 0, width, 0, 0, width, height)
    }
}