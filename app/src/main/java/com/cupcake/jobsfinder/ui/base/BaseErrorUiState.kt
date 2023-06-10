package com.cupcake.jobsfinder.ui.base

sealed class BaseErrorUiState {
    object Disconnected : BaseErrorUiState()
    object UnAuthorized: BaseErrorUiState()
    object ServerError: BaseErrorUiState()
    object NoFoundError : BaseErrorUiState()
}
