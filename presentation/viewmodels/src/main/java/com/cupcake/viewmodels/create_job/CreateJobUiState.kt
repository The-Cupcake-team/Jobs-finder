package com.cupcake.viewmodels.create_job

import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.jobs.JobTitleUiState

data class CreateJobUiState(
    val jobFormUiState: JobFormUiState = JobFormUiState(),
    val buttonText: String = "",
    val isLoading: Boolean = true,
    val error: BaseErrorUiState? = null,
) {
    data class JobFormUiState(
        var jobTitles: List<JobTitleUiState> = emptyList(),
        var company: String = "",
        var workType: String = "",
        var jobType: String = "",
        var jobLocation: String = "",
        val experience: String = "",
        val education: String = "",
        var skills: List<String> = emptyList(),
        val startRangSalary: String = "0",
        var endRangSalary: String = "10000",
        val experienceRequirement: String = "",
        val jobDescription: String = "",
        var salary: Double = 0.0,
    )
}