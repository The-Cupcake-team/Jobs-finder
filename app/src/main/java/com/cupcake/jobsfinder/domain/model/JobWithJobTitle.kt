package com.cupcake.jobsfinder.domain.model

data class JobWithJobTitle(
    val id: String?,
    val jobTitle: JobTitle?,
    val company: String?,
    val createdTime: String?,
    val workType: String?,
    val jobLocation: String?,
    val jobType: String?,
    val jobDescription: String?,
    val salary: String?
)