package com.cupcake.ui.profile.jobs

import com.cupcake.viewmodels.profile.jobs.ProfileJobUiState

sealed class ProfileJobsItem( val data: List<Any>) {

    data class RecentJobs(val items: List<ProfileJobUiState>) : ProfileJobsItem(items)

    data class SavedJobs(val items: List<ProfileJobUiState>) : ProfileJobsItem (items)
}