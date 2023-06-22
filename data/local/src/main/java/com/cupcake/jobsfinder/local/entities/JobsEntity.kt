package com.cupcake.jobsfinder.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs_table")
data class JobsEntity (
    @PrimaryKey
    val id: String,
    val jobId: String,
    val jobTitle: String,
    val company: String,
    val createdTime: Long,
    val workType: String,
    val jobLocation: String,
    val jobType: String,
    val jobDescription: String,
    val minSalary: Double,
    val maxSalary: Double,
    val jobExperience: String,
    val education: String,
    )