package com.cupcake.viewmodels.job_search

import com.cupcake.models.Job
import com.cupcake.viewmodels.base.BaseErrorUiState

data class JopSearchUIState(
    var searchInput: String = "",
    val searchResult: List<JobItemUiState> = emptyList(),
    val jobFilterUIState: JobFilterUIState = JobFilterUIState(),
    val isLoading: Boolean = false,
    val error: BaseErrorUiState? = null,
)

data class JobFilterUIState(
    var location: String = "",
    var jobType: String = "",
    var workType: String = "",
    var experience: String = "",
    var salary: SalaryUIState = SalaryUIState(),
)
data class SalaryUIState(
    var minSalary: Double = 0.0,
    var maxSalary: Double = 0.0
)

data class JobItemUiState(
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
)



fun Job.toJobItemUiState() = JobItemUiState(
    id = id,
    image = "https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://coursera-course-photos.s3.amazonaws.com/e3/f27630d13511e88dd241e68ded0cea/K_logo_800x800.png?auto=format%2Ccompress&dpr=1",
    title = this.jobTitle.title ?: "",
    companyName = this.company,
    detailsChip = listOf(this.workType, this.jobType),
    location = this.jobLocation,
    salary = "$${this.jobSalary.minSalary}-${this.jobSalary.maxSalary}"
)