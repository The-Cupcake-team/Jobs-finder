package com.cupcake.viewmodels.create_job

data class CreateJobUiState(
    val jobFormUiState: JobFormUiState = JobFormUiState(),
//    val screenIcon: ScreenIcon,
    val buttonText: String = "",
    val formNumber: String = "",
    val jobType: String = "",
    val workType: String = "",
    val experience: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
) {
    data class JobFormUiState(
        var jobTitleId: String = "",
        var company: String = "",
        var workType: String = "",
        var jobType: String = "",
        var jobLocation: String = "",
        var jobDescription: String = "",
        var salary: Double = 0.0,
    )
}