package com.cupcake.jobsfinder.domain.model

data class JobTitle(
    val companyName: String,
    val workType: String,
    val location: String,
    val jobType: String,
    val jobDescription: String,
    val salary : Double
)
