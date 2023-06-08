package com.cupcake.jobsfinder.ui.jobs


data class JobsUiState(
    val job: List<JobUiState> = emptyList(),
    val isLoading: Boolean = false,
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
    val salary: String = "",
    val createdAt:Long=0,
)
data class JobDetailUiState(
    val job: JobsDetailsUiState ,
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
)