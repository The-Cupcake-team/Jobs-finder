package com.cupcake.repository.mapper

import com.cupcake.jobsfinder.local.entities.JobsEntity
import com.cupcake.models.Job
import com.cupcake.models.JobSalary
import com.cupcake.models.JobTitle
import com.cupcake.remote.response.job.JobDto

fun JobDto.toJobWithJobTitle(): Job {
    return Job(
        id = id ?: "",
        jobTitle = JobTitle(
            id = jobTitle?.id ?: -1,
            title = jobTitle?.title ?: ""
        ),
        company = company ?: "",
        createdAt = createdAt ?: "",
        workType = workType ?: "",
        jobLocation = jobLocation ?: "",
        jobDescription = jobDescription ?: "",
        jobType = jobType ?: "",
        jobSalary = JobSalary(
            minSalary = jobSalary?.minSalary ?: 0.0,
            maxSalary = jobSalary?.maxSalary ?: 0.0
        ),
        jobExperience = experience ?: "",
        education = eduction ?: "",
        skills = skills?.split(",") ?: emptyList()

    )
}

private fun formatLargeNumber(number: Double): String {
    val suffixes = listOf("", "k", "M", "B", "T") // Add more suffixes as needed
    val suffixIndex = (Math.floor(Math.log10(number)) / 3).toInt()

    val scaledNumber = number / Math.pow(10.0, suffixIndex * 3.toDouble())
    val formattedNumber = String.format("%.1f", scaledNumber)

    return formattedNumber + suffixes[suffixIndex]
}


fun Job.toJobsEntity(): JobsEntity{
    return JobsEntity(
        id = id,
        jobId = jobTitle.id.toString(),
        jobTitle = jobTitle.title ?: "",
        company = company,
        createdTime = createdAt,
        workType = workType,
        jobLocation = jobLocation,
        jobType = jobType,
        jobDescription = jobDescription,
        minSalary = jobSalary.minSalary,
        maxSalary = jobSalary.maxSalary,
        jobExperience = jobExperience,
        education = education
    )
}

fun JobDto.toJob(): Job {
    return Job(
        id = id ?: "",
        jobTitle = jobTitle?.toJobTitle() ?: JobTitle(
            jobTitle?.id ?: -1,
            jobTitle?.title ?: ""
        ),
        company = company ?: "",
        createdAt = createdAt ?: "",
        workType = workType ?: "",
        jobLocation = jobLocation ?: "",
        jobType = jobType ?: "",
        jobDescription = jobDescription ?: "",
        jobSalary = JobSalary(
            maxSalary = jobSalary?.maxSalary ?: 0.0,
            minSalary = jobSalary?.minSalary ?: 0.0
        ),
        jobExperience = experience ?: "",
        education = eduction ?: "",
        skills = skills?.split(",") ?: emptyList()
    )
}

fun JobsEntity.toJob(): Job{
    return Job(
        id = id,
        jobTitle = JobTitle(
            id = jobId.toInt(),
            title = jobTitle
        ),
        company = company,
        createdAt = createdTime,
        workType = workType,
        jobLocation = jobLocation,
        jobDescription = jobDescription,
        jobType = jobType,
        jobSalary = JobSalary(minSalary = minSalary, maxSalary = maxSalary),
        jobExperience = jobExperience,
        education = education,
        skills = emptyList()
    )
}

