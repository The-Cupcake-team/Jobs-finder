package com.cupcake.models

sealed class ErrorType(message: String = "") : Throwable(message) {
    class Network(message: String) : ErrorType(message)
    class Server(message: String) : ErrorType(message)
    class UnAuthorized(message: String) : ErrorType(message)
    class Unknown(message: String) : ErrorType(message)
    object ExpireTokenError : ErrorType()
}