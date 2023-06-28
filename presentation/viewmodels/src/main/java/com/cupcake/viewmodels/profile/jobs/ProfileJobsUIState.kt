package com.cupcake.viewmodels.profile.jobs

import android.os.Parcelable
import com.cupcake.viewmodels.base.BaseErrorUiState
import kotlinx.parcelize.Parcelize

data class ProfileJobsUIState (
    val RecentJobs: List<ProfileJobUiState> = emptyList(),
    val SavedJobs: List<ProfileJobUiState> = emptyList(),
    val isLoading: Boolean = true,
    val error: BaseErrorUiState? = null
        )

@Parcelize
data class ProfileJobUiState(
    val id : String = "",
    val image: String = "",
    val title: String = "",
    val companyName: String = "",
    val detailsChip: List<String> = emptyList(),
    val location: String = "",
    val salary: String = "",
    val createdAt: String = "",
    val jobExperience: String = "",
    val education: String = "",
): Parcelable