//package com.cupcake.usecase.validation
//
//import javax.inject.Inject
//
//class ValidateLoginFormUseCase @Inject constructor(
//    private val validateUsernameUseCase: ValidateUsernameUseCase,
//    private val validatePasswordUseCase: ValidatePasswordUseCase
//){
//    operator fun invoke(userName:String,password:String):Boolean{
//        return validateUsernameUseCase(userName).successful && validatePasswordUseCase(password).successful
//    }
//}