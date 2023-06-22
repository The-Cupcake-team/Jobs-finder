package com.cupcake.usecase.validation


import com.cupcake.models.ErrorType
import javax.inject.Inject


class ValidateUsernameUseCase @Inject constructor(
) {
    operator fun invoke(username: String) {
        if (username.isEmpty() || username.isBlank()) {
            throw ErrorType.InvalidFieldUserName(ERROR)
        } else if (username.length < MINIMUM_LENGTH) {
            throw ErrorType.InvalidFieldUserName(ERROR_LENGTH_SHORT)
        } else if (username.length > MAXIMUM_LENGTH) {
            throw ErrorType.InvalidFieldUserName(ERROR_LENGTH_LARGE)
        } else if (!Regex("^[a-zA-Z\\-]+$").matches(username)) {
            throw ErrorType.InvalidFieldUserName(ERROR_LETTER)
        }

    }

    companion object {
        private const val MINIMUM_LENGTH = 6
        private const val MAXIMUM_LENGTH = 250
        private const val ERROR_LENGTH_SHORT = "Please write at least 6 length of username"
        private const val ERROR_LENGTH_LARGE = "Please write at most 250 length of username"
        private const val ERROR_LETTER = "The user name must be only letters or dash"
        private const val ERROR = "Please enter a user name"

    }
}