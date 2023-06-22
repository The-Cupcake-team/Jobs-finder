package com.cupcake.viewmodels.base

sealed class BaseErrorUiState(val errorCode: String) {
    class Disconnected(val error: String) : BaseErrorUiState(error)
    class UnAuthorized(val error: String) : BaseErrorUiState(error)
    class ServerError(val error: String) : BaseErrorUiState(error)
    class NoFoundError(val error: String) : BaseErrorUiState(error)
    class InvalidFieldPassword(message:String): BaseErrorUiState(message)
    class InvalidFieldUserName(message:String): BaseErrorUiState(message)
    class InvalidFieldFullName(message:String): BaseErrorUiState(message)
    class InvalidFieldConfirmedPassword(message:String): BaseErrorUiState(message)
    class InvalidFieldEmail(message:String): BaseErrorUiState(message)
}
