package com.cupcake.jobsfinder.ui.jobs

import com.cupcake.jobsfinder.ui.base.ErrorUiState


data class JobsUiState(
    val job: List<JobUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: List<ErrorUiState> = emptyList()
)


data class JobUiState(
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val detailsChip: List<String> = emptyList(),
    val location: String = "",
    val salary: String = ""
)