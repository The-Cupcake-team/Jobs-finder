package com.cupcake.usecase.validation


import com.cupcake.models.ErrorType
import javax.inject.Inject


class ValidateUsernameUseCase @Inject constructor(
) {
    operator fun invoke(username: String){
        if (username.isEmpty()) {
            throw ErrorType.InvalidFieldUserName(Error)
        } else if (username.length < MINIMUM_LENGTH) {
            throw ErrorType.InvalidFieldUserName(Error_LENGTH)
        } else if (!Regex("^[a-zA-Z\\-]+$").matches(username)) {
            throw ErrorType.InvalidFieldUserName(Error_LETTER)
        }

    }

    companion object {
        private const val MINIMUM_LENGTH = 4
        private const val Error_LENGTH = "Please write at least 4 length of username"
        private const val Error_LETTER = "The user name must be only letters or dash"
        private const val Error = "Please enter a user name"

    }
}