package com.cupcake.ui.login

import android.os.Bundle
import android.view.View
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentLoginBinding
import com.cupcake.viewmodels.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    R.layout.fragment_login,
    LoginViewModel::class.java
) {
    override val LOG_TAG :String= this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}