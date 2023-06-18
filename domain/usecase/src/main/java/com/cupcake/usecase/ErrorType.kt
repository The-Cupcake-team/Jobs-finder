package com.cupcake.usecase

sealed class ErrorType(val messages: String) : Throwable() {
    class InvalidFieldPassword(message:String=""): ErrorType(message)
    class InvalidFieldUserName(message:String=""): ErrorType(message)
    class InvalidFieldFullName(message:String=""): ErrorType(message)
    class InvalidFieldConfirmedPassword(message:String=""): ErrorType(message)
    class InvalidFieldEmail(message:String=""): ErrorType(message)
}
