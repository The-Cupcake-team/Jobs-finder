package com.cupcake.jobsfinder.ui.create_job

sealed class CreateJobEvent {
    data class PageScrolled(val index: Int) : CreateJobEvent()
}