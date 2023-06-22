package com.cupcake.viewmodels.register

import androidx.lifecycle.viewModelScope
import com.cupcake.models.User
import com.cupcake.usecase.register.RegisterUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<RegisterUiState>(RegisterUiState()) {

    private val _event = MutableSharedFlow<RegisterEvent>()
    val event = _event.asSharedFlow()

    fun register() {
        updateState {
            it.copy(
                isLoading = true,
                fullNameError = "",
                userNameError = "",
                emailError = "",
                passwordError = "",
                confirmedPasswordError = "",
                isFullNameValid = true,
                isUserNameValid = true,
                isEmailValid = true,
                isPasswordValid = true,
                isConfirmedPasswordValid = true
            )
        }
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
        updateState { it.copy(fullName = fullName, fullNameError = "") }
    }

    fun onUserNameChange(userName: String) {
        updateState { it.copy(userName = userName, userNameError = "") }
    }

    fun onEmailChange(email: String) {
        updateState { it.copy(email = email , emailError = "") }
    }

    fun onPasswordChange(password: String) {
        updateState { it.copy(password = password, passwordError = "") }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        updateState { it.copy(confirmedPassword = confirmPassword, confirmedPasswordError = "") }
    }

    fun onLoginClick() {
        viewModelScope.launch { _event.emit(RegisterEvent.LoginClick) }
    }
}
