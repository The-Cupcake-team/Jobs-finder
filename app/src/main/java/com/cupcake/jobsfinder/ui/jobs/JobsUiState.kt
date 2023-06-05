package com.cupcake.jobsfinder.ui.jobs


data class JobsUiState(
    val job: List<JobUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: List<String> = emptyList()
)


data class JobUiState(
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val detailsChip: List<String> = emptyList(),
    val location: String = "",
    val salary: String = ""
)