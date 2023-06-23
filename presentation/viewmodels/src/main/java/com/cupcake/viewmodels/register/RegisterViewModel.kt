package com.cupcake.viewmodels.register

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.register.RegisterUseCase
import com.cupcake.usecase.validation.ValidateConfirmedPasswordUseCase
import com.cupcake.usecase.validation.ValidateEmailUseCase
import com.cupcake.usecase.validation.ValidateFullNameUseCase
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
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validateFullName: ValidateFullNameUseCase,
    private val validateUsername: ValidateUsernameUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateConfirmedPassword: ValidateConfirmedPasswordUseCase
) : BaseViewModel<RegisterUiState>(RegisterUiState()) {

    private val _event = MutableSharedFlow<RegisterEvent>()
    val event = _event.asSharedFlow()

    fun register() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            callee = {
                registerUseCase(
                    fullName = state.value.fullName,
                    userName = state.value.userName,
                    email = state.value.email,
                    password = state.value.password,
                    confirmPassword = state.value.confirmedPassword
                )
            },
            onSuccess = ::onRegisterSuccess,
            onError = ::onRegisterError
        )
    }

    private fun onRegisterSuccess(unit: Unit) {
        updateState { it.copy(isLoading = false) }
        viewModelScope.launch { _event.emit(RegisterEvent.NavigateToHome) }
    }

    private fun onRegisterError(error: BaseErrorUiState) {
        updateState { it.copy(isLoading = false) }
        viewModelScope.launch { _event.emit(RegisterEvent.ShowErrorMessage(error.errorCode)) }
    }

    fun onFullNameChange(fullName: String) {
        val fullNameValidation = validateFullName(fullName)
        updateState {
            it.copy(
                fullName = fullName,
                fullNameError = fullNameValidation.errorMessage,
                isFullNameValid = fullNameValidation.isValid
            )
        }
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

    fun onEmailChange(email: String) {
        val emailValidation = validateEmail(email)
        updateState {
            it.copy(
                email = email,
                emailError = emailValidation.errorMessage,
                isEmailValid = emailValidation.isValid
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

    fun onConfirmPasswordChange(confirmPassword: String) {
        val confirmPasswordValidation = validateConfirmedPassword(
            password = state.value.password,
            confirmedPassword = confirmPassword
        )
        updateState {
            it.copy(
                confirmedPassword = confirmPassword,
                confirmedPasswordError = confirmPasswordValidation.errorMessage,
                isConfirmedPasswordValid = confirmPasswordValidation.isValid
            )
        }
    }

    fun onLoginClick() {
        viewModelScope.launch { _event.emit(RegisterEvent.LoginClick) }
    }
}
