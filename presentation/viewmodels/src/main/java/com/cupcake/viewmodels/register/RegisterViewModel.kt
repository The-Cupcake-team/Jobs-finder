package com.cupcake.viewmodels.register

import androidx.lifecycle.viewModelScope
import com.cupcake.models.ErrorType
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
        updateState { it.copy(isLoading = false, error = error) }
        viewModelScope.launch { _event.emit(RegisterEvent.ShowErrorMessage(error.errorCode)) }
    }

    fun onFullNameChange(fullName: String) {
        viewModelScope.launch {
            try {
                updateState {
                    it.copy(
                        fullName = fullName,
                        fullNameError = "",
                        isFullNameValid = true
                    )
                }
                registerUseCase(
                    fullName,
                    _state.value.userName,
                    _state.value.email,
                    _state.value.password,
                    _state.value.confirmedPassword
                )
            } catch (e: ErrorType) {
                updateState {
                    it.copy(
                        fullName = fullName,
                        fullNameError = (e as? ErrorType.InvalidFieldFullName)?.message ?: "",
                        isFullNameValid = e !is ErrorType.InvalidFieldFullName
                    )
                }
            }
        }
    }

    fun onUserNameChange(userName: String) {
        viewModelScope.launch {
            try {
                updateState {
                    it.copy(
                        userName = userName,
                        userNameError = "",
                        isUserNameValid = true
                    )
                }
                registerUseCase(
                    _state.value.fullName,
                    userName,
                    _state.value.email,
                    _state.value.password,
                    _state.value.confirmedPassword
                )
            } catch (e: ErrorType) {
                updateState {
                    it.copy(
                        userName = userName,
                        userNameError = (e as? ErrorType.InvalidFieldUserName)?.message ?: "",
                        isUserNameValid = e !is ErrorType.InvalidFieldUserName
                    )
                }
            }
        }
    }

    fun onEmailChange(email: String) {
        viewModelScope.launch {
            try {
                updateState {
                    it.copy(
                        email = email,
                        emailError = "",
                        isEmailValid = true
                    )
                }
                registerUseCase(
                    _state.value.fullName,
                    _state.value.userName,
                    email,
                    _state.value.password,
                    _state.value.confirmedPassword
                )
            } catch (e: ErrorType) {
                updateState {
                    it.copy(
                        email = email,
                        emailError = (e as? ErrorType.InvalidFieldEmail)?.message ?: "",
                        isEmailValid= e !is ErrorType.InvalidFieldEmail
                    )
                }
            }
        }
    }

    fun onPasswordChange(password: String) {
        viewModelScope.launch {
            try {
                updateState {
                    it.copy(
                        password= password,
                        passwordError = "",
                        isPasswordValid = true
                    )
                }
                registerUseCase(
                    _state.value.fullName,
                    _state.value.userName,
                    _state.value.email,
                    password,
                    _state.value.confirmedPassword
                )
            } catch (e: ErrorType) {
                updateState {
                    it.copy(
                        password = password,
                        passwordError = (e as? ErrorType.InvalidFieldPassword)?.message ?: "",
                        isPasswordValid = e !is ErrorType.InvalidFieldPassword
                    )
                }
            }
        }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        viewModelScope.launch {
            try {
                updateState {
                    it.copy(
                        confirmedPassword = confirmPassword,
                        confirmedPasswordError = "",
                        isConfirmedPasswordValid = true
                    )
                }
                registerUseCase(
                    _state.value.fullName,
                    _state.value.userName,
                    _state.value.email,
                    _state.value.password,
                    confirmPassword
                )
            } catch (e: ErrorType) {
                updateState {
                    it.copy(
                        confirmedPassword = confirmPassword,
                        confirmedPasswordError = (e as? ErrorType.InvalidFieldConfirmedPassword)?.message ?: "",
                        isConfirmedPasswordValid = e !is ErrorType.InvalidFieldConfirmedPassword
                    )
                }
            }
        }
    }

    fun onLoginClick() {
        viewModelScope.launch { _event.emit(RegisterEvent.LoginClick) }
    }
}
