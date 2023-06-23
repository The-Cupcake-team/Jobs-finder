package com.cupcake.viewmodels.job_details

import com.cupcake.models.Job
import com.cupcake.viewmodels.base.BaseErrorUiState

data class JobDetailsUiState(
    val job: JobDetailsFieldState = JobDetailsFieldState(),
    val isLoading: Boolean = true,
    val error: BaseErrorUiState? = null
)

data class JobDetailsFieldState(
    val id: String = "",
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val workType: String = "",
    val jobType: String = "",
    val location: String = "",
    val salary: Int = 0,
    val createdAt: String = "",
    val description: String = "",
    val experience: String = ""
)

fun Job.toJobsDetailsUiState(): JobDetailsFieldState {
    return JobDetailsFieldState(
        id = id,
        image = "",
        title = jobTitle.title ?: "",
        companyName = company,
        workType = workType,
        jobType = jobType,
        location = jobLocation,
        salary = jobSalary.maxSalary.toInt(),
        createdAt = createdAt,
        description = jobDescription,
        experience = jobExperience
    )
}

