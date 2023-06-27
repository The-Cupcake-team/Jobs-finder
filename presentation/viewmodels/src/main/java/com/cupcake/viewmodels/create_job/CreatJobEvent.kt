package com.cupcake.viewmodels.create_job

sealed class CreateJobEvent {
    object NextPage: CreateJobEvent()
    object JobCreated: CreateJobEvent()
    class ShowError(val message: String): CreateJobEvent()
}