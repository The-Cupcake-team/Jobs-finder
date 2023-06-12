package com.cupcake.models


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