package com.kaushiknsanji.coroutinesroom.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kaushiknsanji.coroutinesroom.R
import com.kaushiknsanji.coroutinesroom.utils.common.observeEvent
import com.kaushiknsanji.coroutinesroom.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*

/**
 * Fragment that inflates the layout 'R.layout.fragment_sign_up' for user registration.
 *
 * @author Kaushik N Sanji
 */
class SignUpFragment : Fragment() {

    companion object {
        /**
         * Factory method that provides the instance of [SignUpFragment]
         */
        fun newInstance() = SignUpFragment()
    }

    // Instance of Fragment's ViewModel
    private val viewModel: SignUpViewModel by viewModels()

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
     * @return Return the View for the fragment's UI (R.layout.fragment_sign_up), or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
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

        // Register Click listener on "Sign Up" button
        btn_sign_up.setOnClickListener {
            // Delegate to the ViewModel to handle
            viewModel.onSignUp(
                edit_sign_up_username.text.toString(),
                edit_sign_up_password.text.toString(),
                edit_sign_up_other_info.text.toString()
            )
        }

        // Register Click listener on "Go To Login" button
        btn_sign_up_login.setOnClickListener { viewModel.onGoToLogin() }
    }

    /**
     * Method that initializes [androidx.lifecycle.LiveData] Observers.
     */
    private fun setupObservers() {

        // Register an observer for Main Fragment launch events
        viewModel.launchMain.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(SignUpFragmentDirections.toDestMain())
        }

        // Register an observer for Login Fragment launch events
        viewModel.launchLogin.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(SignUpFragmentDirections.toDestLogin())
        }

    }
}
