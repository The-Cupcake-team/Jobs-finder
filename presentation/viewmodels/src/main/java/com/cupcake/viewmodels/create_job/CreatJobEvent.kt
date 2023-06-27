package com.cupcake.viewmodels.create_job

sealed class CreateJobEvent {
    object NextPage: CreateJobEvent()
}