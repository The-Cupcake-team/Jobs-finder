package com.cupcake.usecase.register

import com.cupcake.models.ValidationResult
import com.cupcake.usecase.validation.ValidateConfirmedPasswordUseCase
import com.cupcake.usecase.validation.ValidateEmailUseCase
import com.cupcake.usecase.validation.ValidateFullNameUseCase
import com.cupcake.usecase.validation.ValidateJobTitleUseCase
import com.cupcake.usecase.validation.ValidatePasswordUseCase
import com.cupcake.usecase.validation.ValidateUsernameUseCase
import javax.inject.Inject

class ValidateRegisterFormUseCase @Inject constructor(
    private val validateFullName: ValidateFullNameUseCase,
    private val validateUsername: ValidateUsernameUseCase,
    private val validateJobTitle: ValidateJobTitleUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateConfirmedPassword: ValidateConfirmedPasswordUseCase,
) {
    operator fun invoke(
        fullName: String,
        userName: String,
        email: String,
        jobTitle: String,
        password: String,
        confirmedPassword: String
    ): List<ValidationResult> {
        return listOf(
            validateFullName(fullName),
            validateUsername(userName),
            validateJobTitle(jobTitle),
            validateEmail(email),
            validatePassword(password),
            validateConfirmedPassword(confirmedPassword, password)
        )
    }
}