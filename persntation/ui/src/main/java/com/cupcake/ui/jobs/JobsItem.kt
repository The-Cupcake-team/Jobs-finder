package com.cupcake.ui.jobs

import com.cupcake.viewmodels.job_details.JobDetailsUiState

sealed class JobsItem(val rank: Int, val data: List<JobDetailsUiState>) {
    data class Recommended(val items: List<JobDetailsUiState>) : JobsItem(0, items)
    data class TopSalary(val items: List<JobDetailsUiState>) : JobsItem(1, items)
    data class LocationJobs(val items: List<JobDetailsUiState>) : JobsItem(2, items)
}
