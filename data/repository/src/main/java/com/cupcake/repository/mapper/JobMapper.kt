package com.cupcake.repository.mapper

import com.cupcake.models.Job
import com.cupcake.remote.response.job.JobDto

fun JobDto.toJob(): Job {
    return Job(
        jobTitleId = 123,
        id =  id ?: "438cd8f7-af62-4796-84be-a98807e874d8",
        company = company ?: "The Chance",
        createdAt = createdAt ?: 1234,
        workType = workType ?: "full time",
        jobLocation = jobLocation ?: "Iraq",
        jobType = jobType ?: "Front-End",
        jobDescription = jobDescription ?: "Job description",
        jobSalary = jobSalary ?: 12.34
    )
}