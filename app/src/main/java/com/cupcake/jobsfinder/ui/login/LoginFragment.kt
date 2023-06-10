package com.cupcake.jobsfinder.ui.login

import android.os.Bundle
import android.view.View
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentLoginBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>(
    R.layout.fragment_login,
    LoginViewModel::class.java
) {
    override val LOG_TAG :String= this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}