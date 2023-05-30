package com.cupcake.jobsfinder.data.remote.model

data class JobDto(
    val id: Long?,
    val jobTitle: String?,
    val company: String?,
    val createdTime : String?,
    val workType: String?,
    val jobLocation: String?,
    val jobDescription: String?,
    val price: String?
)
