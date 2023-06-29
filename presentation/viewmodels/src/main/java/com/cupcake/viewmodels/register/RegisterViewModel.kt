package com.cupcake.viewmodels.register

import androidx.lifecycle.viewModelScope
import com.cupcake.models.User
import com.cupcake.usecase.GetAllJobTitleUseCase
import com.cupcake.usecase.register.RegisterUseCase
import com.cupcake.usecase.validation.ValidateConfirmedPasswordUseCase
import com.cupcake.usecase.validation.ValidateEmailUseCase
import com.cupcake.usecase.validation.ValidateFullNameUseCase
import com.cupcake.usecase.validation.ValidateJobTitleUseCase
import com.cupcake.usecase.validation.ValidatePasswordUseCase
import com.cupcake.usecase.validation.ValidateUsernameUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.jobs.JobTitleUiState
import com.cupcake.viewmodels.jobs.toJobTitleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val jobTitles: GetAllJobTitleUseCase,
    private val validateFullName: ValidateFullNameUseCase,
    private val validateUsername: ValidateUsernameUseCase,
    private val validateJobTitle: ValidateJobTitleUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateConfirmedPassword: ValidateConfirmedPasswordUseCase,
) : BaseViewModel<RegisterUiState>(RegisterUiState()) {

    private val _event = MutableSharedFlow<RegisterEvent>()
    val event = _event.asSharedFlow()

    private var searchJobTitle: Job? = null

    fun register() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            callee = {
                registerUseCase(
                    fullName = state.value.fullName,
                    userName = state.value.userName,
                    email = state.value.email,
                    jobTitle = state.value.jobTitle,
                    password = state.value.password,
                    confirmPassword = state.value.confirmedPassword,
                    jobTitleId = state.value.jobTitleId
                )
            },
            onSuccess = ::onRegisterSuccess,
            onError = ::onError
        )
    }

    private fun onRegisterSuccess(user: User) {
        updateState { it.copy(isLoading = false) }
        viewModelScope.launch { _event.emit(RegisterEvent.NavigateToHome) }
    }

    fun onJobTitleChange(query: CharSequence) {
        val validateJobTitle = validateJobTitle(query.toString())
        updateState {
            it.copy(
                jobTitleError = validateJobTitle.errorMessage,
                isJobTitleValid = validateJobTitle.isValid
            )
        }

        getJobTitles(validateJobTitle.isValid, query.toString())
    }

    private fun getJobTitles(isValid: Boolean, query: String) {
        if (isValid) {
            searchJobTitle?.cancel()
            searchJobTitle = viewModelScope.launch {
                //delay(200)
                tryToExecute(
                    { jobTitles(query).map { it.toJobTitleUiState() } },
                    ::onGetJobTitleSuccess,
                    ::onError
                )
            }
        }
    }

    private fun onGetJobTitleSuccess(jobTitles: List<JobTitleUiState>) {
        _state.update {
            it.copy(
                jobTitles = jobTitles,
                jobTitle = jobTitles.firstOrNull()?.title ?: "",
                jobTitleId = jobTitles.firstOrNull()?.id ?: 0,
                isJobTitleValid = jobTitles.isNotEmpty()
            )
        }
    }

    private fun onError(error: BaseErrorUiState) {
        if (error is BaseErrorUiState.UnAuthorized) {
            updateState {
                it.copy(
                    isLoading = false,
                    fullNameError = error.validationResults.component1().errorMessage,
                    isFullNameValid = error.validationResults.component1().isValid,
                    userNameError = error.validationResults.component2().errorMessage,
                    isUserNameValid = error.validationResults.component2().isValid,
                    jobTitleError = error.validationResults.component3().errorMessage,
                    isJobTitleValid = error.validationResults.component3().isValid,
                    emailError = error.validationResults.component4().errorMessage,
                    isEmailValid = error.validationResults.component4().isValid,
                    passwordError = error.validationResults.component5().errorMessage,
                    isPasswordValid = error.validationResults.component5().isValid,
                    confirmedPasswordError = error.validationResults[5].errorMessage,
                    isConfirmedPasswordValid = error.validationResults[5].isValid
                )
            }
        } else {
            updateState { it.copy(isLoading = false) }
            viewModelScope.launch { _event.emit(RegisterEvent.ShowErrorMessage(error.errorCode)) }
        }

    }

    fun onFullNameChange(fullName: String) {
        val validationResult = validateFullName(fullName)
        updateState {
            it.copy(
                fullName = fullName,
                fullNameError = validationResult.errorMessage,
                isFullNameValid = validationResult.isValid
            )
        }
    }

    fun onUserNameChange(userName: String) {
        val validationResult = validateUsername(userName)
        updateState {
            it.copy(
                userName = userName,
                userNameError = validationResult.errorMessage,
                isUserNameValid = validationResult.isValid
            )
        }
    }

    fun onEmailChange(email: String) {
        val validationResult = validateEmail(email)
        updateState {
            it.copy(
                email = email,
                emailError = validationResult.errorMessage,
                isEmailValid = validationResult.isValid
            )
        }
    }

    fun onPasswordChange(password: String) {
        val validationResult = validatePassword(password)
        updateState {
            it.copy(
                password = password,
                passwordError = validationResult.errorMessage,
                isPasswordValid = validationResult.isValid
            )
        }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        val validationResult = validateConfirmedPassword(
            password = state.value.password,
            confirmedPassword = confirmPassword
        )
        updateState {
            it.copy(
                confirmedPassword = confirmPassword,
                confirmedPasswordError = validationResult.errorMessage,
                isConfirmedPasswordValid = validationResult.isValid
            )
        }
    }

    fun onLoginClick() {
        viewModelScope.launch { _event.emit(RegisterEvent.LoginClick) }
    }
}
