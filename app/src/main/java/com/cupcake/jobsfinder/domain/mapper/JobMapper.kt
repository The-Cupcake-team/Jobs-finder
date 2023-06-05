package com.cupcake.jobsfinder.domain.mapper

import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.data.remote.response.job.JobWithTitleDto
import com.cupcake.jobsfinder.domain.model.Job
import com.cupcake.jobsfinder.domain.model.JobTitle
import com.cupcake.jobsfinder.domain.model.JobWithTitle

fun JobWithTitleDto.toJobWithJobTitle(): JobWithTitle {
    return JobWithTitle(
        id = id,
        jobTitle = JobTitle(
            id = jobTitle.id,
            title = jobTitle.title
        ),
        company = company,
        createdTime = createdAt.toString(),
        workType = workType,
        jobLocation = jobLocation,
        jobDescription = jobDescription,
        jobType = jobType,
        salary = jobSalary.toString()
    )
}

fun JobDto.toJob(): Job {
    return Job(
        jobTitleId = jobTitleId ?: 0,
        company = company ?: "",
        workType = workType ?: "",
        jobLocation = jobLocation ?: "",
        jobDescription = jobDescription ?: "",
        jobType = jobType ?: "",
        jobSalary = jobSalary ?: .0,
        createdAt = createdAt ?: 0L
    )
}

fun Job.toJobDto(): JobDto {
    return JobDto(
        jobTitleId = jobTitleId,
        company = company,
        workType = workType,
        jobLocation = jobLocation,
        jobDescription = jobDescription,
        jobType = jobType,
        jobSalary = jobSalary
    )
}