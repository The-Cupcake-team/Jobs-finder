package com.cupcake.viewmodels.register


sealed class RegisterEvent {
    object LoginClick : RegisterEvent()

    class ShowErrorMessage(val errorMessage: String) : RegisterEvent()
}