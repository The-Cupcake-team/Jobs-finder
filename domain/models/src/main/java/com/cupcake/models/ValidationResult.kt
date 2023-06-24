package com.cupcake.models

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String = ""
)