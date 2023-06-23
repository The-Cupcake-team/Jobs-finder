package com.cupcake.viewmodels.create_job

import com.cupcake.models.JobSalary
import com.cupcake.models.JobTitle
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.jobs.JobTitleUiState

data class CreateJobUiState(
    val jobFormUiState: JobFormUiState = JobFormUiState(),
    val buttonText: String = "",
    val isLoading: Boolean = true,
    val error: BaseErrorUiState? = null,
    val progressStep: Int = 0

) {

    data class JobFormUiState(
        var jobTitles: List<JobTitleUiState> = emptyList(),
        var jobTitleUIState: JobTitleUIState = JobTitleUIState(),
        var company: String = "",
        var workType: String = "",
        var jobType: String = "",
        var jobLocation: String = "",
        val experience: String = "",
        val education: String = "",
        var skills: List<String> = emptyList(),
        var experienceRequirement: String = "",
        var jobDescription: String = "",
        var salary: JobSalaryUIState = JobSalaryUIState(),
    )

    data class JobTitleUIState(
        var id: Int = 1,
        var title: String = "",
    )

    data class JobSalaryUIState(
        var maxSalary: Double = 0.0,
        var minSalary: Double = 0.0,
    )
}