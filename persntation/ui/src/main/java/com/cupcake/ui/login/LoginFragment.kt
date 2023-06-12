package com.cupcake.ui.login

import android.os.Bundle
import android.view.View
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentLoginBinding
import com.cupcake.viewmodels.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel<Any?>>(
    R.layout.fragment_login,
    LoginViewModel::class.java
) {
    override val LOG_TAG :String= this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}