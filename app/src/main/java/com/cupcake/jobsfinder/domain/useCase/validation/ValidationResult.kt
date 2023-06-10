package com.cupcake.jobsfinder.domain.usecase.validation


data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
