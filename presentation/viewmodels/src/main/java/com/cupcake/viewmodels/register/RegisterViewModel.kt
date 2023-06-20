package com.cupcake.viewmodels.register

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.models.User
import com.cupcake.usecase.RegisterUseCase
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
                    confirmPassword = state.value.confirmPassword
                )
            },
            onSuccess = ::onSignUpSuccess,
            onError = ::onSignUpError
        )
    }

    private fun onSignUpSuccess(user: User) {
        updateState { it.copy(isLoading = false, isUserRegistered = true) }
        Log.i("Register", "onSignUpSuccess: ${user.fullName}")
    }

    private fun onSignUpError(error: BaseErrorUiState) {
        updateState { it.copy(isLoading = false, error = error) }
        Log.i("Register", "onSignUpError: ${error.errorCode}")
        viewModelScope.launch { _event.emit(RegisterEvent.ShowErrorMessage(error.errorCode)) }
    }

    fun onFullNameChange(fullName: String) {
        updateState { it.copy(fullName = fullName) }
    }

    fun onUserNameChange(userName: String) {
        updateState { it.copy(userName = userName) }
    }

    fun onEmailChange(email: String) {
        updateState { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        updateState { it.copy(password = password) }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        updateState { it.copy(confirmPassword = confirmPassword) }
    }

    fun onLoginClick() {
        viewModelScope.launch { _event.emit(RegisterEvent.LoginClick) }
    }

}