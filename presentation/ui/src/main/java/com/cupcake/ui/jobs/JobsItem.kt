package com.cupcake.ui.jobs

import com.cupcake.viewmodels.jobs.JobTitleUiState
import com.cupcake.viewmodels.jobs.JobUiState

sealed class JobsItem(val rank: Int, val data: List<Any>) {
    data class PopularJobs(val items: List<JobTitleUiState>) : JobsItem(0, items)
    data class Recommended(val items: List<JobUiState>) : JobsItem(1, items)
    data class TopSalary(val items: List<JobUiState>) : JobsItem(2, items)
    data class LocationJobs(val items: List<JobUiState>) : JobsItem(3, items)
}
