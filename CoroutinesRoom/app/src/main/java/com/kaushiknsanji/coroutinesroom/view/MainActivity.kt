package com.kaushiknsanji.coroutinesroom.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.kaushiknsanji.coroutinesroom.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity of the App that inflates the layout 'R.layout.activity_main'.
 *
 * @author Kaushik N Sanji
 */
class MainActivity : AppCompatActivity() {

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

        // Initialize Toolbar as ActionBar
        setSupportActionBar(toolbar_main)

        // Find Navigation Controller instance
        val navController = findNavController(R.id.fragment_main_nav_host)

        // Create AppBarConfiguration with Top-level destinations
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.dest_sign_up, R.id.dest_login, R.id.dest_main)
        )

        // Setup ActionBar with NavController to show the appropriate label
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
