package com.cupcake.viewmodels.create_job

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
        val jobTitles: List<JobTitleUiState> = emptyList(),
        val jobTitleUIState: JobTitleUiState = JobTitleUiState(),
        val company: String = "",
        val workType: String = WorkType.FULL_TIME.value,
        val jobType: String = JobType.ON_SITE.value,
        val jobLocation: String = "",
        val experience: String = ExperienceType.ENTRY.value,
        val education: String = "",
        val skills: List<String> = emptyList(),
        val experienceRequirement: String = "",
        val jobDescription: String = "",
        val salary: JobSalaryUIState = JobSalaryUIState(),
    )

    data class JobTitleUIState(
        var id: Int = 1,
        var title: String = "",
    )

    data class JobSalaryUIState(
        var maxSalary: Double = 0.0,
        var minSalary: Double = 100000.0,
    )
}