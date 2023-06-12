package com.cupcake.ui.base

sealed class BaseErrorUiState {
    object Disconnected : BaseErrorUiState()
    object UnAuthorized: BaseErrorUiState()
    object ServerError: BaseErrorUiState()
    object NoFoundError : BaseErrorUiState()
}
