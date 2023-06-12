package com.cupcake.viewmodels.jobs

import com.cupcake.models.JobWithTitle
import com.cupcake.viewmodels.job_details.JobDetailsUiState


data class JobsUiState(
    val recommendedJobs: List<JobDetailsUiState> = emptyList(),
    val topSalaryJobs: List<JobDetailsUiState> = emptyList(),
    val inLocationJobs: List<JobDetailsUiState> = emptyList(),
    val isLoading: Boolean = true,
    val error: List<String> = emptyList()
)

data class ErrorUiState(
    val code : Int,
    val message : String
)




fun JobWithTitle.toJobUiState() = JobDetailsUiState(
        image = "https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://coursera-course-photos.s3.amazonaws.com/e3/f27630d13511e88dd241e68ded0cea/K_logo_800x800.png?auto=format%2Ccompress&dpr=1",
        title = this.jobTitle.title,
        companyName = this.company,
        detailsChip = listOf(this.workType, this.jobType),
        location = this.jobLocation,
        salary = this.salary
    )

