package com.cupcake.viewmodels.job_details

import com.cupcake.models.Job
import com.cupcake.viewmodels.base.BaseErrorUiState

data class JobDetailsUiState(
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val detailsChip: List<String> = emptyList(),
    val location: String = "",
    val salary: String = "",
    val createdAt: Long = 0,
)

data class JobDetailUiState(
    val job: JobsDetailsUiState = JobsDetailsUiState(),
    val isLoading: Boolean = true,
    val error: BaseErrorUiState? = null
)

data class JobsDetailsUiState(
    val id: String = "",
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val workType: String = "",
    val jobType: String = "",
    val location: String = "",
    val salary: Int = 0,
    val createdAt: Long = 0,
    val description: String = "",
    val experience: String = ""
)

fun Job.toJobsDetailsUiState(): JobsDetailsUiState {
    return JobsDetailsUiState(
        id = this.id,
        image = "",
        title = this.jobTitle.title,
        companyName = this.company,
        workType = this.workType,
        jobType = this.jobType,
        location = this.jobLocation,
        salary = this.jobSalary.toInt(),
        createdAt = this.createdAt,
        description = this.jobDescription,
        experience = this.jobExperience
    )
}

