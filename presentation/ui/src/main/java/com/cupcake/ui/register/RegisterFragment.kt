package com.cupcake.ui.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentRegisterBinding
import com.cupcake.ui.post.CreatePostFragment
import com.cupcake.viewmodels.register.RegisterEvent
import com.cupcake.viewmodels.register.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(
    R.layout.fragment_register,
    RegisterViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleRegisterEvents()
    }

    private fun handleRegisterEvents() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.event.collect {
                when (it) {
                    is RegisterEvent.LoginClick -> navigateToLogin()
                    is RegisterEvent.ShowError -> showSnackBar()
                    is RegisterEvent.NavigateToHome -> navigateToHome()

                }
            }
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }

    private fun showSnackBar() {
        Snackbar.make(requireView(), R.string.offline, Snackbar.LENGTH_LONG).show()
    }

    private fun navigateToHome() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToPostsFragment())
    }
}