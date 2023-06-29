package com.cupcake.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentLoginBinding
import com.cupcake.ui.register.RegisterFragmentDirections
import com.cupcake.viewmodels.login.LoginEvent
import com.cupcake.viewmodels.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    R.layout.fragment_login,
    LoginViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleLoginEvents()
    }

    private fun handleLoginEvents() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.event.collect {
                when (it) {
                    is LoginEvent.RegisterClick -> navigateToRegister()
                    is LoginEvent.ShowErrorMessage -> showSnackBar(it.errorMessage)
                    is LoginEvent.NavigateToHome -> navigateToHome()
                }
            }
        }
    }

    private fun navigateToRegister() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }

    private fun showSnackBar(errorMessage: String) {
        Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private fun navigateToHome() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPostsFragment())
    }

}