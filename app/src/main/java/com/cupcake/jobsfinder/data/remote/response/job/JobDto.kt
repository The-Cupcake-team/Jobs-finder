package com.cupcake.jobsfinder.data.remote.response.job


import kotlinx.serialization.Serializable

@Serializable
data class JobDto(
    val id: String? = null,
    val jobTitleId: Long?,
    val company: String?,
    val createdAt: Long? = null,
    val workType: String?,
    val jobLocation: String?,
    val jobType: String?,
    val jobDescription: String?,
    val jobSalary: String?
)