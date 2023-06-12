package com.cupcake.ui.create_job


data class CreateJobUiState(
    val jobFormUiState: JobFormUiState = JobFormUiState(),
    val activeIconToolBar: Int = 0,
    val iconCloseToolBar: Int = 0,
    val iconBackToolBar: Int = 0,
    val titleToolBar: Int = 0,
    val formNumber: Int = 0,
    val isLoading: Boolean = false,
    val isValidForm: Boolean = false,
    val error: String = "",
) {
    data class JobFormUiState(
        val idJobTitle: Int = 0,
        val company: String = "",
        val workType: String = "",
        val jobType: String = "",
        val jobLocation: String = "",
        val jobDescription: String = "",
        val salary: Double = 0.0,
    )
}