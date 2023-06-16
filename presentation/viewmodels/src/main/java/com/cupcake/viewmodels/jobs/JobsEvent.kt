package com.cupcake.viewmodels.jobs

sealed class JobsEvent {
    data class JobCardClick(val id: String) : JobsEvent()
    data class JobChipClick(val id: String) : JobsEvent()
}