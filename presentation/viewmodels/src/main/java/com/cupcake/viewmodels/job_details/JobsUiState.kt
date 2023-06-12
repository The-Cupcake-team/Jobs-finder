package com.cupcake.viewmodels.job_details

import com.cupcake.models.JobWithTitle

data class JobDetailsUiState(
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val detailsChip: List<String> = emptyList(),
    val location: String = "",
    val salary: String = "",
    val createdAt:Long=0,
)
data class JobDetailUiState(
    val job: JobsDetailsUiState,
    val isLoading: Boolean = false,
    val error: List<String> = emptyList()
)
data class JobsDetailsUiState(
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val workType:String="",
    val jobType:String = "",
    val location: String = "",
    val salary: String = "",
    val createdAt:Long=0,
    val jobDescription:String=""
)


fun JobWithTitle.toJobUiState() = JobDetailsUiState(
        image = "https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://coursera-course-photos.s3.amazonaws.com/e3/f27630d13511e88dd241e68ded0cea/K_logo_800x800.png?auto=format%2Ccompress&dpr=1",
        title = this.jobTitle.title,
        companyName = this.company,
        detailsChip = listOf(this.workType, this.jobType),
        location = this.jobLocation,
        salary = this.salary
    )

