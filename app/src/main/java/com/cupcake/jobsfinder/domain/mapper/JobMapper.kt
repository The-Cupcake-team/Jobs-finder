package com.cupcake.jobsfinder.domain.mapper

import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.domain.model.Job

fun JobDto.toJob(): Job{
    return Job(
        id = this.id,
        jobTitleId = this.jobTitleId,
        company = this.company,
        createdTime = this.createdAt.toString(),
        workType = this.workType,
        jobLocation = this.jobLocation,
        jobDescription = this.jobDescription,
        jobType = this.jobType,
        salary = this.jobSalary.toString()
    )
}