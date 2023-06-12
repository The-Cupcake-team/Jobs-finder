package com.cupcake.viewmodels.login

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.LoginUseCase
import com.cupcake.usecase.validation.ValidateLoginFormUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.usecase.validation.ValidatePasswordUseCase
import com.cupcake.usecase.validation.ValidateUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateUsernameUseCase: ValidateUsernameUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateLoginFormUseCase: ValidateLoginFormUseCase,
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginUiState>(LoginUiState()) {

    private val _loginUIState = MutableStateFlow(LoginUiState())
    val loginUIState = _loginUIState.asStateFlow()

    fun userNameChanged(userName: String) {
        val validateResult = validateUsernameUseCase(userName)
        val isValidFields = validateLoginFormUseCase(userName, _loginUIState.value.password)
        val isUserNameValid = validateResult.successful

        _loginUIState.update {
            it.copy(
                userName = userName,
                userNameError = validateResult.errorMessage ?: "",
                isValidFields = isValidFields,
                isUserNameValid = isUserNameValid
            )
        }
    }


    fun passwordChanged(password: String) {
        val validateResult = validatePasswordUseCase(password)
        val isValidFields = validateLoginFormUseCase(_loginUIState.value.userName, password)
        val isPasswordValid = validateResult.successful

        _loginUIState.update {
            it.copy(
                password = password,
                passwordError = validateResult.errorMessage ?: "",
                isValidFields = isValidFields,
                isPasswordValid = isPasswordValid
            )
        }
    }


    fun login() {
        viewModelScope.launch {
            try {
                _loginUIState.update {
                    it.copy(
                        isLoading = true,
                        error = ""
                    )
                }
                val loginResult =
                    loginUseCase(loginUIState.value.userName, loginUIState.value.password)
                if (loginResult) {
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