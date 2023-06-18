package com.cupcake.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentLoginBinding
import com.cupcake.ui.databinding.FragmentRegisterBinding
import com.cupcake.viewmodels.login.LoginViewModel
import com.cupcake.viewmodels.register.RegisterViewModel

class RegisterFragment: BaseFragment<FragmentRegisterBinding ,  RegisterViewModel>(

    R.layout.fragment_register,
    RegisterViewModel::class.java
    ) {
        override val LOG_TAG :String= this.javaClass.name

    }

