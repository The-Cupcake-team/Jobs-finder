package com.cupcake.jobsfinder.ui.jobs

sealed class JobsItem(val rank: Int, val data: List<JobUiState>) {
    data class Recommended(val items: List<JobUiState>) : JobsItem(0, items)
    data class TopSalary(val items: List<JobUiState>) : JobsItem(1, items)
    data class LocationJobs(val items: List<JobUiState>) : JobsItem(2, items)
}
