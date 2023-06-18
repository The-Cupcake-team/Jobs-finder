package com.cupcake.usecase.validation

import com.cupcake.usecase.ErrorType
import javax.inject.Inject


class ValidateUsernameUseCase @Inject constructor() {
    operator fun invoke(username: String) {
        lessThanMinimum(username)
        checkCharacter(username)
        checkIfEmpty(username)
    }

    private fun checkIfEmpty(username: String) {
        if (username.isEmpty()) {
            throw  ErrorType.InvalidFieldUserName(Error)
        }
    }

    private fun lessThanMinimum(password: String) {
        if (password.length < MINIMUM_LENGTH){
            throw  ErrorType.InvalidFieldUserName(Error_LENGTH)
        }
    }

    private fun checkCharacter(fullName: String) {
        val regex = Regex("^[a-zA-Z\\-]+$")
        if (!regex.matches(fullName) )
            throw  ErrorType.InvalidFieldUserName(Error_LETTER)
    }


    companion object {
        private const val MINIMUM_LENGTH = 4
        private const val Error_LENGTH = "Please write at least 4 length of username"
        private const val Error_LETTER = "The user name must be only letters or dash"
        private const val Error = "Please enter a user name"

    }
}