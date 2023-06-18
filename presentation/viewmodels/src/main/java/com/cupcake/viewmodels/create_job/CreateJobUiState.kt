package com.cupcake.viewmodels.create_job

import com.cupcake.viewmodels.base.BaseErrorUiState

data class CreateJobUiState(
    val jobFormUiState: JobFormUiState = JobFormUiState(),
//    val screenIcon: ScreenIcon,
    val buttonText: String = "",
    val formNumber: String = "",
    val isLoading: Boolean = false,
    val error: BaseErrorUiState? = null,
) {
    data class JobFormUiState(
        var jobTitleId: String = "",
        var company: String = "",
        var workType: String = "",
        var jobType: String = "",
        var jobLocation: String = "",
        val experience: String = "",
        val education: String = "",
        var skills: String = "",
        val startRangSalary: String = "0",
        var endRangSalary: String = "10000",
        val experienceRequirement: String = "",
        val jobDescription: String = "",
        var salary: Double = 0.0,
    )
}