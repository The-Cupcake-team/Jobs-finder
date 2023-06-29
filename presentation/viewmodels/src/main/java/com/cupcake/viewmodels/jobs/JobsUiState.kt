package com.cupcake.viewmodels.jobs

import android.os.Parcelable
import com.cupcake.models.Job
import com.cupcake.models.JobSalary
import com.cupcake.models.JobTitle
import com.cupcake.viewmodels.base.BaseErrorUiState
import kotlinx.parcelize.Parcelize


data class JobsUiState(
    val popularJobs: List<JobTitleUiState> = emptyList(),
    val recommendedJobs: List<JobUiState> = emptyList(),
    val topSalaryJobs: List<JobUiState> = emptyList(),
    val onLocationJobs: List<JobUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isSavedJob: Boolean = false,
    val error: BaseErrorUiState? = null,
)

data class ErrorUiState(
    val code: Int,
    val message: String
)

@Parcelize
data class JobUiState(
    val id : String = "",
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val detailsChip: List<String> = emptyList(),
    val location: String = "",
    val minSalary: Double = 0.0,
    val maxSalary: Double = 0.0,
    val createdAt: String = "",
    val jobExperience: String = "",
    val education: String = "",
): Parcelable


data class JobTitleUiState(
    val id: Int = 0,
    val title: String = ""
)

fun JobTitle.toJobTitleUiState() = JobTitleUiState(
    id = id ?: -1,
    title = this.title ?: ""
)

fun JobUiState.toJob(): Job{
    return Job(
        id = id,
        jobTitle = JobTitle(title = title, id = id.toInt()),
        company = companyName,
        createdAt = createdAt,
        workType = detailsChip[0],
        jobLocation = location,
        jobType = detailsChip[1],
        jobDescription = companyName,
        jobSalary = JobSalary(minSalary = minSalary, maxSalary = maxSalary),
        jobExperience = jobExperience,
        education = education,
        skills = emptyList(),
    )
}

data class JobDetailUiState(
    val job: JobsDetailsUiState,
    val isLoading: Boolean = false,
    val error: List<String> = emptyList()
) {
    data class JobsDetailsUiState(
        val image: String = "",
        val title: String = "",
        val companyName: String = "",
        val workType: String = "",
        val jobType: String = "",
        val location: String = "",
        val salary: String = "",
        val createdAt: Long = 0,
        val jobDescription: String = ""
    )
}


