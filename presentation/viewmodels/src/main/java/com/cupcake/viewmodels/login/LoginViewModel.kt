package com.cupcake.viewmodels.login

import androidx.lifecycle.viewModelScope
import com.cupcake.models.ErrorType
import com.cupcake.usecase.LoginUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase,
) : BaseViewModel<LoginUiState>(LoginUiState()) {
    fun login() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true) }
                _state.update {
                    it.copy(
                        userName = _state.value.userName,
                        userNameError = "",
                        isUserNameValid = true,
                        password = _state.value.password,
                        passwordError = "",
                        isPasswordValid = true
                    )
                }
                login(_state.value.userName, _state.value.password)


                onSuccessLogin()
            } catch (e: ErrorType) {

//                _state.update {
//                    it.copy(
//                        userName = _state.value.userName,
//                        password = _state.value.password,
//                        userNameError = if (e is ErrorType.InvalidFieldUserName) e.messages else "",
//                        passwordError = if (e is ErrorType.InvalidFieldPassword) e.messages else "",
//                        isUserNameValid =  e !is  ErrorType.InvalidFieldUserName  ,
//                        isPasswordValid = e !is ErrorType.InvalidFieldPassword
//                    )
//                }
//                onErrorLogin(e.message ?: "Unknown error")

            }
        }
    }
    private fun onErrorLogin(error: BaseErrorUiState) {
        _state.update {
            it.copy(
                isLoading = false,
                error = error
            )
        }
    }

    private fun onSuccessLogin() {
//        _state.update {
//            it.copy(
//                isLoading = false,
//                error = "",
//            )
//        }
    }
}