package com.cupcake.viewmodels.register


sealed class RegisterEvent {
    object LoginClick : RegisterEvent()

    object ShowError : RegisterEvent()

    object NavigateToHome : RegisterEvent()
}