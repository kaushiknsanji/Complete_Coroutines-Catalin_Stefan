package com.kaushiknsanji.coroutinesroom.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kaushiknsanji.coroutinesroom.R
import com.kaushiknsanji.coroutinesroom.data.local.db.entity.User
import com.kaushiknsanji.coroutinesroom.utils.common.observeEvent
import com.kaushiknsanji.coroutinesroom.utils.common.observeNonNull
import com.kaushiknsanji.coroutinesroom.utils.common.observeNull
import com.kaushiknsanji.coroutinesroom.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Fragment that inflates the layout 'R.layout.fragment_main', to show the
 * logged-in user information.
 *
 * @author Kaushik N Sanji
 */
class MainFragment : Fragment() {

    companion object {
        /**
         * Factory method that provides the instance of [MainFragment]
         */
        fun newInstance() = MainFragment()
    }

    // Instance of Fragment's ViewModel
    private val viewModel: MainViewModel by viewModels()

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI (R.layout.fragment_main), or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    /**
     * Called immediately after [.onCreateView]
     * has returned, but before any saved state has been restored in to the view.
     *
     * @param view The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup any LiveData Observers
        setupObservers()

        // Register Click listener on "Log Out" button
        btn_main_log_out.setOnClickListener { viewModel.onLogOut() }

        // Register Click listener on "Delete user" button
        btn_main_delete_user.setOnClickListener { viewModel.onDeleteUser() }
    }

    /**
     * Method that initializes [androidx.lifecycle.LiveData] Observers.
     */
    private fun setupObservers() {

        // Register an observer on Logged-in User LiveData to set the Username
        // on the corresponding textView
        viewModel.loggedInUser.observeNonNull(viewLifecycleOwner) { user: User ->
            text_main_username.text = user.username
        }

        // Register an observer on InfoText LiveData to set the corresponding textView
        viewModel.loggedInUserInfoText.observeNull(viewLifecycleOwner) { infoText: String? ->
            if (infoText.isNullOrBlank()) {
                // When Info is not available or blank, hide the TextView
                text_main_info.visibility = View.GONE
            } else {
                // When Info is available, show the TextView and set the text
                text_main_info.visibility = View.VISIBLE
                text_main_info.text = getString(R.string.text_main_info_placeholder, infoText)
            }
        }

        // Register an observer for SignUp Fragment launch events
        viewModel.launchSignUp.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(MainFragmentDirections.toDestSignUp())
        }

        // Register an observer for Delete User Confirmation dialog launch events
        viewModel.launchConfirmDelete.observeEvent(viewLifecycleOwner) {
            // Create and show the dialog only when this fragment is still attached to its activity
            activity?.let {
                AlertDialog.Builder(it)
                    .setTitle(R.string.title_dialog_main_confirm_delete_user)
                    .setPositiveButton(R.string.label_dialog_main_confirm_delete_user_yes) { _, _ ->
                        // On Confirmation, delegate to the ViewModel to handle
                        viewModel.onConfirmDeleteUser()
                    }
                    .setNegativeButton(R.string.label_dialog_main_confirm_delete_user_cancel, null)
                    .create()
                    .show()
            }
        }

        // Register an observer for message-id LiveData, to show a Toast for the
        // corresponding message from String resources
        viewModel.messageStringId.observeEvent(viewLifecycleOwner) { messageResId: Int ->
            showMessage(messageResId)
        }

        // Register an observer for error LiveData, to show a Toast for the error message
        viewModel.error.observeEvent(viewLifecycleOwner) { errorMessage: String ->
            showMessage("Error: $errorMessage")
        }

        // Register an observer for error-id LiveData, to show a Toast for the
        // corresponding error message from String resources
        viewModel.errorStringId.observeEvent(viewLifecycleOwner) { errorResId: Int ->
            showMessage("Error: ${getString(errorResId)}")
        }

    }

    /**
     * Displays a [android.widget.Toast] for the [message] string.
     */
    private fun showMessage(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    /**
     * Displays a [android.widget.Toast] for the message Resource id [messageResId].
     */
    private fun showMessage(@StringRes messageResId: Int) = showMessage(getString(messageResId))

}
