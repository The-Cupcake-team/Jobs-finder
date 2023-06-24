package com.cupcake.models


data class Job(
    val id: String,
    val jobTitle: JobTitle,
    val company: String,
    val createdAt: String,
    val workType: String,
    val jobLocation: String,
    val jobType: String,
    val jobDescription: String,
    val jobSalary: JobSalary,
    val jobExperience: String,
    val education: String,
    val skills: List<String>
)