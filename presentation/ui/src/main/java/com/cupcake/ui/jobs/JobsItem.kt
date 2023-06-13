package com.cupcake.ui.jobs

import com.cupcake.viewmodels.jobs.JobUiState

sealed class JobsItem(val rank: Int, val data: List<Any>) {
    data class Recommended(val items: List<JobUiState>) : JobsItem(0, items)
    data class TopSalary(val items: List<JobUiState>) : JobsItem(1, items)
    data class LocationJobs(val items: List<JobUiState>) : JobsItem(2, items)
}
