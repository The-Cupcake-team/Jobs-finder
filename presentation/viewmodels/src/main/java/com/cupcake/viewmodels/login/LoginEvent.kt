package com.cupcake.viewmodels.login


sealed class LoginEvent {
    object RegisterClick : LoginEvent()

    class ShowErrorMessage(val errorMessage: String) : LoginEvent()

    object NavigateToHome : LoginEvent()
}