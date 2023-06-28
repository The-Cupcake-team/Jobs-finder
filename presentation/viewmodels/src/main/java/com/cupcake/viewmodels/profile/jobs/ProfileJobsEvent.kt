package com.cupcake.viewmodels.profile.jobs



sealed class ProfileJobsEvent {

    data class JobCardClick(val id: String) : ProfileJobsEvent()

    data class OnMoreOptionClickListener(val model: ProfileJobUiState): ProfileJobsEvent()

    data class JobChipClick(val title: String) : ProfileJobsEvent()

}