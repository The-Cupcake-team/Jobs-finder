package com.cupcake.jobsfinder.ui.jobs

import com.cupcake.jobsfinder.domain.model.JobWithTitle


data class JobsUiState(
    val recommendedJobs: List<JobUiState> = emptyList(),
    val topSalaryJobs: List<JobUiState> = emptyList(),
    val inLocationJobs: List<JobUiState> = emptyList(),
    val isLoading: Boolean = true,
    val error: List<String> = emptyList()
)

data class ErrorUiState(
    val code : Int,
    val message : String
)

data class JobUiState(
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val detailsChip: List<String> = emptyList(),
    val location: String = "",
    val salary: String = ""
)

fun JobWithTitle.toJobUiState() = JobUiState(
        image = "",
        title = this.jobTitle.title,
        companyName = this.company,
        detailsChip = listOf(this.workType, this.jobType),
        location = this.jobLocation,
        salary = this.salary
    )
