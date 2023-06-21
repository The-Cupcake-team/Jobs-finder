package com.cupcake.viewmodels.jobs


sealed class JobsEvent {
    data class JobCardClick(val id: String) : JobsEvent()

    object OnFloatingActionClickListener : JobsEvent()

    data class OnMoreOptionClickListener(val model:JobUiState): JobsEvent()

    data class JobChipClick(val title: String) : JobsEvent()

    object SearchBoxClick : JobsEvent()

}