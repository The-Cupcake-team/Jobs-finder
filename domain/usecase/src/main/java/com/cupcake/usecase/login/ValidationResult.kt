package com.cupcake.usecase.login


data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
