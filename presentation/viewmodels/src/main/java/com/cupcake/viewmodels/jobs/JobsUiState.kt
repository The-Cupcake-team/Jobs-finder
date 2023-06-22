package com.cupcake.viewmodels.jobs

import android.os.Parcelable
import com.cupcake.models.Job
import com.cupcake.models.JobSalary
import com.cupcake.models.JobTitle
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.job_details.JobDetailsUiState
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
    val salary: String = "",
    val createdAt: Long = 0,
    val jobExperience: String = "",
    val education: String = "",
): Parcelable


data class JobTitleUiState(
    val id: String = "",
    val title: String = ""
)

fun JobTitle.toJobTitleUiState() = JobTitleUiState(
    id = this.id ?: "",
    title = this.title ?: ""
)

fun JobUiState.toJob(): Job{
    return Job(
        id = id,
        jobTitle = JobTitle(title = title, id = id),
        company = companyName,
        createdAt = createdAt,
        workType = detailsChip[0],
        jobLocation = location,
        jobType = detailsChip[1],
        jobDescription = companyName,
        jobSalary = JobSalary(minSalary = salary.toDouble(), maxSalary = salary.toDouble()),
        jobExperience = jobExperience,
        education = education
    )
}
data class JobDetailUiState(
    val job: JobsDetailsUiState,
    val isLoading: Boolean = false,
    val error: List<String> = emptyList()
)

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


fun Job.toJobUiState() = JobUiState(
    id = id,
    image = "https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://coursera-course-photos.s3.amazonaws.com/e3/f27630d13511e88dd241e68ded0cea/K_logo_800x800.png?auto=format%2Ccompress&dpr=1",
    title = this.jobTitle.title ?: "",
    companyName = this.company,
    detailsChip = listOf(this.workType, this.jobType),
    location = this.jobLocation,
    salary = this.jobSalary.toString()
)


