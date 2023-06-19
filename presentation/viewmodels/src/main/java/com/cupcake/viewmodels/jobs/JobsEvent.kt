package com.cupcake.viewmodels.jobs

sealed class JobsEvent {
    data class JobCardClick(val id: String) : JobsEvent()
    data class JobChipClick(val title: String) : JobsEvent()

    object SearchBoxClick : JobsEvent()

    object SaveButtonClick : JobsEvent()
}