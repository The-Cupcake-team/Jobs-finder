package com.cupcake.viewmodels.base

import com.cupcake.models.ValidationResult
import kotlinx.parcelize.Parcelize

sealed class BaseErrorUiState(val errorCode: String) {
    class Disconnected(val error: String) : BaseErrorUiState(error)
    class UnAuthorized(val validationResults: List<ValidationResult>) :
        BaseErrorUiState("Invalid Input")

    class ServerError(val error: String) : BaseErrorUiState(error)
    class NoFoundError(val error: String) : BaseErrorUiState(error)
}
