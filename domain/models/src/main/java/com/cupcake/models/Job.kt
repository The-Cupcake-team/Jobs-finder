package com.cupcake.models


class Job(
    val id: String,
    val jobTitle: JobTitle,
    val company: String,
    val createdAt: Long,
    val workType: String,
    val jobLocation: String,
    val jobType: String,
    val jobDescription: String,
    val jobSalary: Double,
    val jobExperience: String,
)