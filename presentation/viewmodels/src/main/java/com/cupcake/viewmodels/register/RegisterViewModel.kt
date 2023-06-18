package com.cupcake.viewmodels.register

import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

) : BaseViewModel<RegisterUiState>(RegisterUiState()) {

    fun signUp() {
    }

    private fun onSignUpSuccess() {
    }

    private fun onSignUpError(error: BaseErrorUiState) {
    }

}