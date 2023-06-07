package com.cupcake.jobsfinder.ui.jobs

sealed class JobsItem(val rank: Int, items: List<JobUiState>){
    data class Recommended(val items: List<JobUiState>): JobsItem(0, items)
    data class TopSalary(val items: List<JobUiState>): JobsItem(1, items)
    data class LocationJobs(val item: JobUiState): JobsItem(2, listOf(item))
}
