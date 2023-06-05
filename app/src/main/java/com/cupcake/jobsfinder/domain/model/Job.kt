package com.cupcake.jobsfinder.domain.model

data class Job(
    val jobTitleId: Int?,
    val company: String?,
    val workType: String?,
    val jobLocation: String?,
    val jobType : String?,
    val jobDescription: String?,
    val jobSalary: Double?
)

