package com.cupcake.jobsfinder.data.model

data class JobTitleDto(
    val id: Long,
    val idJobTitle: Long,
    val companyName: String,
    val createdAt: String,
    val workType: String,
    val location: String,
    val jobType: String,
    val jobDescription: String,
    val salary : Double
    )
