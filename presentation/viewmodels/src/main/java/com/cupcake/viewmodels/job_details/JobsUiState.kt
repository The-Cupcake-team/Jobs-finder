package com.cupcake.viewmodels.job_details

import com.cupcake.viewmodels.base.BaseErrorUiState

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
    val job: JobsDetailsUiState = JobsDetailsUiState(),
    val isLoading: Boolean = true,
    val error: BaseErrorUiState? = null
)
data class JobsDetailsUiState(
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val workType:String="",
    val jobType:String = "",
    val location: String = "",
    val salary: Int = 0,
    val createdAt:Long=0,
    val jobDescription:String=""
)

