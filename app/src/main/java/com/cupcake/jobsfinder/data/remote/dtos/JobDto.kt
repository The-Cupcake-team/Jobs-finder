package com.cupcake.jobsfinder.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class JobDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("jobTitleId")
    val jobTitleId: Long?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("createdAt")
    val createdAt: Long?,
    @SerializedName("workType")
    val workType: String?,
    @SerializedName("jobLocation")
    val jobLocation: String?,
    @SerializedName("jobType")
    val jobType: String?,
    @SerializedName("jobDescription")
    val jobDescription: String?,
    @SerializedName("jobSalary")
    val jobSalary: Long?
)