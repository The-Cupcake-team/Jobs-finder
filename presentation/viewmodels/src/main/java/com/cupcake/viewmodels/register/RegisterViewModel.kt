package com.cupcake.viewmodels.register

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.ErrorType
import com.cupcake.usecase.RegisterUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val register: RegisterUseCase
) : BaseViewModel<RegisterUiState>(RegisterUiState()) {

    fun checkField(fieldValue: String, fieldName: String) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true) }
                _state.update {
                    when (fieldName) {
                        "fullName" -> it.copy(
                            fullName = fieldValue,
                            fullNameError = "",
                            isFullNameValid = true
                        )
                        "userName" -> it.copy(
                            userName = fieldValue,
                            userNameError = "",
                            isUserNameValid = true
                        )
                        "email" -> it.copy(email = fieldValue,
                            emailError = "",
                            isEmailValid = true
                        )
                        "password" -> it.copy(
                            password = fieldValue,
                            passwordError = "",
                            isPasswordValid = true
                        )
                        else -> it.copy(
                            confirmedPassword = fieldValue,
                            confirmedPasswordError = "",
                            isConfirmedPasswordValid = true
                        )
                    }
                }
                when (fieldName) {
                    "fullName" -> register(
                        fieldValue,
                        _state.value.userName,
                        _state.value.email,
                        _state.value.password,
                        _state.value.confirmedPassword
                    )
                    "userName" -> register(
                        _state.value.fullName,
                        fieldValue,
                        _state.value.email,
                        _state.value.password,
                        _state.value.confirmedPassword
                    )
                    "email" -> register(
                        _state.value.fullName,
                        _state.value.userName,
                        fieldValue,
                        _state.value.password,
                        _state.value.confirmedPassword
                    )
                    "password" -> register(
                        _state.value.fullName,
                        _state.value.userName,
                        _state.value.email,
                        fieldValue,
                        _state.value.confirmedPassword
                    )
                    else -> register(
                        _state.value.fullName,
                        _state.value.userName,
                        _state.value.email,
                        _state.value.password,
                        fieldValue
                    )
                }
                onSignUpSuccess()
            } catch (e: ErrorType) {
                _state.update {
                    when (fieldName) {
                        "fullName" -> it.copy(
                            fullName = fieldValue,
                            fullNameError = (e as? ErrorType.InvalidFieldFullName)?.messages ?: "",
                            isFullNameValid = e !is ErrorType.InvalidFieldFullName
                        )
                        "userName" -> it.copy(
                            userName = fieldValue,
                            userNameError = (e as? ErrorType.InvalidFieldUserName)?.messages ?: "",
                            isUserNameValid = e !is ErrorType.InvalidFieldUserName
                        )
                        "email" -> it.copy(
                            email = fieldValue,
                            emailError = (e as? ErrorType.InvalidFieldEmail)?.messages ?: "",
                            isEmailValid = e !is ErrorType.InvalidFieldEmail
                        )
                        "password" -> it.copy(
                            password = fieldValue,
                            passwordError = (e as? ErrorType.InvalidFieldPassword)?.messages ?: "",
                            isPasswordValid = e !is ErrorType.InvalidFieldPassword
                        )
                        else -> it.copy(
                            confirmedPassword = fieldValue,
                            confirmedPasswordError = (e as? ErrorType.InvalidFieldConfirmedPassword)?.messages
                                ?: "",
                            isConfirmedPasswordValid = e !is ErrorType.InvalidFieldConfirmedPassword
                        )
                    }
                }
                onSignUpError(e.message ?: "Unknown error")
            }
        }
    }
    fun checkFullName(fullName: String) {
        checkField(fullName, "fullName")
    }
    fun checkUserName(userName: String) {
        checkField(userName, "userName")
    }
    fun checkEmail(email: String) {
        checkField(email, "email")
    }
    fun checkPassword(password: String) {
        checkField(password, "password")
    }
    fun checkConfirmedPassword(confirmedPassword: String) {
        checkField(confirmedPassword, "confirmedPassword")
    }



    fun signUp() {
//            viewModelScope.launch {
//                try {
//                    _state.update { it.copy(isLoading = true) }
//                    _state.update {
//                        it.copy(
//                            fullName = _state.value.fullName,
//                            userName = _state.value.userName,
//                            email = _state.value.email,
//                            password = _state.value.password,
//                            confirmedPassword = _state.value.confirmedPassword,
//                            fullNameError = "",
//                            userNameError = "",
//                            emailError = "",
//                            confirmedPasswordError = "",
//                            passwordError = "",
//                            isFullNameValid = true,
//                            isUserNameValid = true,
//                            isEmailValid = true,
//                            isPasswordValid = true,
//                            isConfirmedPasswordValid = true
//                        )
//                    }
//                    register(
//                        _state.value.fullName,
//                        _state.value.userName,
//                        _state.value.email,
//                        _state.value.password,
//                        _state.value.confirmedPassword
//                    )
//
//
//                    onSignUpSuccess()
//                } catch (e: ErrorType) {
//                    _state.update {
//                        it.copy(
//                            fullName = _state.value.fullName,
//                            userName = _state.value.userName,
//                            email = _state.value.email,
//                            password = _state.value.password,
//                            confirmedPassword = _state.value.confirmedPassword,
//                            fullNameError = if (e is ErrorType.InvalidFieldFullName) e.messages else "",
//                            userNameError = if (e is ErrorType.InvalidFieldUserName) e.messages else "",
//                            emailError = if (e is ErrorType.InvalidFieldEmail) e.messages else "",
//                            passwordError = if (e is ErrorType.InvalidFieldPassword) e.messages else "",
//                            confirmedPasswordError = if (e is ErrorType.InvalidFieldConfirmedPassword) e.messages else "",
//                            isFullNameValid = e !is ErrorType.InvalidFieldFullName,
//                            isUserNameValid = e !is ErrorType.InvalidFieldUserName,
//                            isEmailValid = e !is ErrorType.InvalidFieldEmail,
//                            isPasswordValid = e !is ErrorType.InvalidFieldPassword,
//                            isConfirmedPasswordValid = e !is ErrorType.InvalidFieldConfirmedPassword
//                        )
//                    }
//                    onSignUpError(e.message ?: "Unknown error")
//
//
//                }
//            }
        }

        private fun onSignUpSuccess() {

        }

        private fun onSignUpError(errorType: String) {
        }
//    fun checkFullName(fullName: String) {
//        viewModelScope.launch {
//            try {
//                _state.update { it.copy(isLoading = true) }
//                _state.update {
//                    it.copy(
//                        fullName = fullName,
//                        fullNameError = "",
//                        isFullNameValid = true,
//                    )
//                }
//                register(
//                    fullName,
//                    _state.value.userName,
//                    _state.value.email,
//                    _state.value.password,
//                    _state.value.confirmedPassword
//                )
//                onSignUpSuccess()
//            } catch (e: ErrorType) {
//                _state.update {
//                    it.copy(
//                        fullName = fullName,
//                        fullNameError = (e as? ErrorType.InvalidFieldFullName)?.messages ?: "",
//                        isFullNameValid = e !is ErrorType.InvalidFieldFullName,
//                    )
//                }
//                onSignUpError(e.message ?: "Unknown error")
//            }
//        }
//    }

    }
