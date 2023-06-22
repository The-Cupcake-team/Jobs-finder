package com.cupcake.viewmodels.register

import androidx.lifecycle.viewModelScope
import com.cupcake.models.User
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
    private val validateFullNameUseCase: ValidateFullNameUseCase,
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

    private fun onRegisterSuccess(user: User) {
        updateState { it.copy(isLoading = false, isUserRegistered = true) }
        viewModelScope.launch { _event.emit(RegisterEvent.NavigateToHome) }
    }

    private fun onRegisterError(error: BaseErrorUiState) {
        when (error) {
            is BaseErrorUiState.InvalidFieldConfirmedPassword -> {
                updateState {
                    it.copy(
                        isLoading = false,
                        confirmedPasswordError = error.errorCode,
                        isConfirmedPasswordValid = false
                    )
                }
            }

            is BaseErrorUiState.InvalidFieldEmail -> {
                updateState {
                    it.copy(
                        isLoading = false,
                        emailError = error.errorCode,
                        isEmailValid = false
                    )
                }
            }

            is BaseErrorUiState.InvalidFieldFullName -> {
                updateState {
                    it.copy(
                        isLoading = false,
                        fullNameError = error.errorCode,
                        isFullNameValid = false
                    )
                }
            }

            is BaseErrorUiState.InvalidFieldPassword -> {
                updateState {
                    it.copy(
                        isLoading = false,
                        passwordError = error.errorCode,
                        isPasswordValid = false
                    )
                }
            }

            is BaseErrorUiState.InvalidFieldUserName -> {
                updateState {
                    it.copy(
                        isLoading = false,
                        userNameError = error.errorCode,
                        isUserNameValid = false
                    )
                }
            }

            else -> {
                updateState { it.copy(isLoading = false, error = error) }
                viewModelScope.launch { _event.emit(RegisterEvent.ShowError) }
            }
        }
    }

    fun onFullNameChange(fullName: String) {
        val error = validateFullNameUseCase(fullName)
        if (error.message!!.isNotBlank()) {
            updateState {
                it.copy(
                    fullName = fullName,
                    fullNameError = error.message.toString(),
                    isFullNameValid = false
                )
            }
        } else {
            updateState { it.copy(fullName = fullName, fullNameError = "", isFullNameValid = true) }
        }
    }

    fun onUserNameChange(userName: String) {
        val error = validateUsername(userName)
        if (error.message!!.isNotBlank()) {
            updateState {
                it.copy(
                    userName = userName,
                    userNameError = error.message.toString(),
                    isUserNameValid = false
                )
            }
        } else {
            updateState { it.copy(fullName = userName, userNameError = "", isUserNameValid = true) }
        }
    }

    fun onEmailChange(email: String) {
        val error = validateEmail(email)
        if (error.message!!.isNotBlank()) {
            updateState {
                it.copy(
                    email = email,
                    emailError = error.message.toString(),
                    isEmailValid = false
                )
            }
        } else {
            updateState { it.copy(email = email, emailError = "", isEmailValid = true) }
        }
    }

    fun onPasswordChange(password: String) {
        val error = validatePassword(password)
        if (error.message!!.isNotBlank()) {
            updateState {
                it.copy(
                    password = password,
                    passwordError = error.message.toString(),
                    isPasswordValid = false
                )
            }
        } else {
            updateState { it.copy(password = password, passwordError = "", isPasswordValid = true) }
        }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        val error = validateConfirmedPassword(confirmPassword, state.value.password)
        if (error.message!!.isNotBlank()) {
            updateState {
                it.copy(
                    confirmedPassword = confirmPassword,
                    confirmedPasswordError = error.message.toString(),
                    isConfirmedPasswordValid = false
                )
            }
        } else {
            updateState {
                it.copy(
                    confirmedPassword = confirmPassword,
                    confirmedPasswordError = "",
                    isConfirmedPasswordValid = true
                )
            }
        }
    }

    fun onLoginClick() {
        viewModelScope.launch { _event.emit(RegisterEvent.LoginClick) }
    }
}
