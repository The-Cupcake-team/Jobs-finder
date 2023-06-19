package com.cupcake.remote.response.job

import com.cupcake.remote.response.JobSalaryDto
import com.cupcake.remote.response.JobTitleDto
import kotlinx.serialization.Serializable

@Serializable
data class JobWithTitleDto(
    val id: String,
    val creatorId: String,
    val jobTitle: JobTitleDto,
    val company: String,
    val createdAt: Long,
    val workType: String,
    val jobLocation: String,
    val jobType: String,
    val jobDescription: String,
    val jobSalary: JobSalaryDto,
    val experience: String,
    val education: String
)
