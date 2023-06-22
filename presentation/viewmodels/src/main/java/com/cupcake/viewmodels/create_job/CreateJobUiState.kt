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
        var jobTitleUIState: JobTitleUIState = JobTitleUIState(),
        var company: String = "",
        var workType: String = "",
        var jobType: String = "",
        var jobLocation: String = "",
        val experience: String = "",
        val education: String = "",
        var skills: String = "",
        var startRangSalary: String = "",
        var endRangSalary: String = "",
        var experienceRequirement: String = "",
        var jobDescription: String = "",
        var salary: JobSalaryUIState = JobSalaryUIState(),
    )

    data class JobTitleUIState(
        var id: String = "",
        var title: String = "",
    )

    data class JobSalaryUIState(
        var maxSalary: Double = 0.0,
        var minSalary: Double = 0.0,
    )
}