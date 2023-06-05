package com.cupcake.jobsfinder.ui.create_job.state

data class CreateJobUiState(
    val jobFormUiState: JobFormUiState = JobFormUiState(),
    val isLoading: Boolean = false,
    val isValidForm: Boolean = false,
    val error: String = "",
)