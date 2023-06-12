package com.cupcake.usecase.validation


data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
