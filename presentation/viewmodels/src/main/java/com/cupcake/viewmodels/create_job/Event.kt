package com.cupcake.viewmodels.create_job

sealed class CreateJobEvent {
    data class PageScrolled(val index: Int) : CreateJobEvent()
}