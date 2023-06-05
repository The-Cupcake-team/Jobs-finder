package com.cupcake.jobsfinder.domain.mapper

import com.cupcake.jobsfinder.data.remote.response.job.JobWithTitleDto
import com.cupcake.jobsfinder.domain.model.JobTitle
import com.cupcake.jobsfinder.domain.model.JobWithJobTitle

fun JobWithTitleDto.toJobWithJobTitle(): JobWithJobTitle {
    return JobWithJobTitle(
        id = this.id,
        jobTitle = JobTitle(
            id = this.jobTitle.id,
            title = this.jobTitle.title
        ),
        company = this.company,
        createdTime = this.createdAt.toString(),
        workType = this.workType,
        jobLocation = this.jobLocation,
        jobDescription = this.jobDescription,
        jobType = this.jobType,
        salary = this.jobSalary.toString()
    )
}