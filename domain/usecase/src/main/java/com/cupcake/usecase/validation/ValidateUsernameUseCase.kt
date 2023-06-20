package com.cupcake.usecase.validation

import com.cupcake.models.ErrorType
import javax.inject.Inject


class ValidateUsernameUseCase @Inject constructor() {
    operator fun invoke(username: String) {
        if (username.isBlank()) {
            throw ErrorType.Validation(ERROR)
        }
        if (username.length < MINIMUM_LENGTH) {
            throw ErrorType.Validation(LENGTH_ERROR)
        }

        val regex = Regex("^[a-zA-Z\\-]+$")
        if (!regex.matches(username)) {
            throw ErrorType.Validation(LETTERS_ERROR)
        }
    }


    companion object {
        private const val MINIMUM_LENGTH = 4
        private const val LENGTH_ERROR = "Please write at least 4 length of username"
        private const val LETTERS_ERROR = "The user name must be only letters or dash"
        private const val ERROR = "Please enter a user name"

    }
}