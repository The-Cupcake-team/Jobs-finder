package com.cupcake.jobsfinder.domain.model

import com.cupcake.jobsfinder.data.remote.response.JobTitleDto

class Job(
    val jobTitleId: Int,
    val company: String,
    val createdAt: Long,
    val workType: String,
    val jobLocation: String,
    val jobType: String,
    val jobDescription: String,
    val jobSalary: Double
)