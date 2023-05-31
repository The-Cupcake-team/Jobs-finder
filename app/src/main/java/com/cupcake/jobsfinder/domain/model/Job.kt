package com.cupcake.jobsfinder.domain.model

data class Job(
    val id: String?,
    val jobTitleId: Long?,
    val company: String?,
    val createdTime : String?,
    val workType: String?,
    val jobLocation: String?,
    val jobType : String?,
    val jobDescription: String?,
    val salary: String?
)