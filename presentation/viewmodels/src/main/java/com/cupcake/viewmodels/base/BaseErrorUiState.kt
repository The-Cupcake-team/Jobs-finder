package com.cupcake.viewmodels.base

sealed class BaseErrorUiState {
    object Disconnected : BaseErrorUiState()
    object UnAuthorized: BaseErrorUiState()
    object ServerError: BaseErrorUiState()
    object NoFoundError : BaseErrorUiState()
}
