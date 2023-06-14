package com.cupcake.viewmodels.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.LoginUseCase
import com.cupcake.usecase.login.ValidateLoginFormUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginForm: ValidateLoginFormUseCase,
    private val loginApi: LoginUseCase,
) : BaseViewModel<LoginUiState>(LoginUiState()) {

    private val _loginUIState = MutableStateFlow(LoginUiState())
    val loginUIState = _loginUIState.asStateFlow()
    var userName: String = ""
    var password: String = ""
    private lateinit var loginResult: ValidateLoginFormUseCase.ValidationResults
    fun login() {
        viewModelScope.launch {
            try {
                _loginUIState.update { it.copy(isLoading = true, error = "") }
                loginResult = validateLoginForm(userName, password)
                if (!loginResult.isUserNameValid) {
                    _loginUIState.update {
                        it.copy(
                            userName = userName,
                            userNameError = loginResult.validateUserName,
                            isUserNameValid = loginResult.isUserNameValid
                        )
                    }
                } else {
                    _loginUIState.update {
                        it.copy(
                            userName = userName,
                            userNameError = loginResult.validateUserName,
                            isUserNameValid = loginResult.isUserNameValid
                        )
                    }
                }
                if (!loginResult.isPasswordValid) {
                    _loginUIState.update {
                        it.copy(
                            password = password,
                            passwordError = loginResult.validatePassword,
                            isPasswordValid = loginResult.isPasswordValid
                        )
                    }
                } else {
                    _loginUIState.update {
                        it.copy(
                            password = password,
                            passwordError = loginResult.validatePassword,
                            isPasswordValid = loginResult.isPasswordValid
                        )
                    }
                }
                if (loginResult.isUserNameValid&&loginResult.isPasswordValid) {
                    loginApi(loginUIState.value.userName, loginUIState.value.password)
                    onSuccessLogin()
                }

            } catch (e: Throwable) {


                onErrorLogin(e.message ?: "Unknown error")

            }
        }
    }

    private fun onErrorLogin(error: String) {
        _loginUIState.update {
            it.copy(
                isLoading = false,
                error = error
            )
        }
    }

    private fun onSuccessLogin() {
        _loginUIState.update {
            it.copy(
                isLoading = false,
                error = "",
            )
        }
    }

}


