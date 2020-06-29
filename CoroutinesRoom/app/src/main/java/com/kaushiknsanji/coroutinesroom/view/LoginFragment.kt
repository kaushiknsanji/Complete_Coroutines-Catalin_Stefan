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
import com.kaushiknsanji.coroutinesroom.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Fragment that inflates the layout 'R.layout.fragment_login' for user login.
 *
 * @author Kaushik N Sanji
 */
class LoginFragment : Fragment() {

    companion object {
        /**
         * Factory method that provides an instance of [LoginFragment].
         */
        fun newInstance() = LoginFragment()
    }

    // Instance of Fragment's ViewModel
    private val viewModel: LoginViewModel by viewModels()

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
     * @return Return the View for the fragment's UI (R.layout.fragment_login), or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
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

        // Register Click listener on Login button
        btn_login.setOnClickListener { viewModel.onLogin() }

        // Register Click listener on "Go To SignUp" button
        btn_login_sign_up.setOnClickListener { viewModel.onGoToSignUp() }
    }

    /**
     * Method that initializes [androidx.lifecycle.LiveData] Observers.
     */
    private fun setupObservers() {

        // Register an observer for Main Fragment launch events
        viewModel.launchMain.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(LoginFragmentDirections.toDestMain())
        }

        // Register an observer for SignUp Fragment launch events
        viewModel.launchSignUp.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(LoginFragmentDirections.toDestSignUp())
        }

    }

}
