package com.cupcake.jobsfinder.domain.mapper

import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.domain.model.Job

fun JobDto.toJob(): Job{
    return Job(
        jobTitleId = jobTitleId,
        company = company,
        workType = workType,
        jobLocation = jobLocation,
        jobDescription = jobDescription,
        jobType = jobType,
        jobSalary = jobSalary
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