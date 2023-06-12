package com.cupcake.ui.create_job

import com.cupcake.viewmodels.create_job.CreateJobEvent

sealed class CreateJobEvent {
    data class PageScrolled(val index: Int) : CreateJobEvent()
}