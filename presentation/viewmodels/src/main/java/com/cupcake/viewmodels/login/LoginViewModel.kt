package com.cupcake.viewmodels.login

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.login.LoginUseCase
import com.cupcake.usecase.validation.ValidatePasswordUseCase
import com.cupcake.usecase.validation.ValidateUsernameUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateUsername: ValidateUsernameUseCase,
    private val validatePassword: ValidatePasswordUseCase,
) : BaseViewModel<LoginUiState>(LoginUiState()) {

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    fun login() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            callee = {
                loginUseCase(
                    userName = state.value.userName,
                    password = state.value.password,
                )
            },
            onSuccess = ::onLoginSuccess,
            onError = ::onLoginError
        )
    }

    private fun onLoginSuccess(unit: Unit) {
        updateState { it.copy(isLoading = false) }
        viewModelScope.launch { _event.emit(LoginEvent.NavigateToHome) }
    }

    private fun onLoginError(error: BaseErrorUiState) {
        updateState { it.copy(isLoading = false) }
        viewModelScope.launch { _event.emit(LoginEvent.ShowErrorMessage(error.errorCode)) }
    }

    fun onUserNameChange(userName: String) {
        val userNameValidation = validateUsername(userName)
        updateState {
            it.copy(
                userName = userName,
                userNameError = userNameValidation.errorMessage,
                isUserNameValid = userNameValidation.isValid
            )
        }
    }

    fun onPasswordChange(password: String) {
        val passwordValidation = validatePassword(password)
        updateState {
            it.copy(
                password = password,
                passwordError = passwordValidation.errorMessage,
                isPasswordValid = passwordValidation.isValid
            )
        }
    }

    fun onRegisterClick() {
        viewModelScope.launch { _event.emit(LoginEvent.RegisterClick) }
    }
}