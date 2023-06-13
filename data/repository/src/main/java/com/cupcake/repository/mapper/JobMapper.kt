package com.cupcake.repository.mapper

import com.cupcake.models.JobTitle
import com.cupcake.models.JobWithTitle
import com.cupcake.remote.response.job.JobWithTitleDto

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

//fun com.cupcake.remote.response.job.JobDto.toJob(): com.cupcake.models.Job {
//    return Job(
//        jobTitleId = jobTitleId ?: 0,
//        company = company ?: "",
//        workType = workType ?: "",
//        jobLocation = jobLocation ?: "",
//        jobDescription = jobDescription ?: "",
//        jobType = jobType ?: "",
//        jobSalary = jobSalary ?: .0,
//        createdAt = createdAt ?: 0L
//    )
//}
//
//fun com.cupcake.models.Job.toJobDto(): com.cupcake.remote.response.job.JobDto {
//    return com.cupcake.remote.response.job.JobDto(
//        jobTitleId = jobTitleId,
//        company = company,
//        workType = workType,
//        jobLocation = jobLocation,
//        jobDescription = jobDescription,
//        jobType = jobType,
//        jobSalary = jobSalary
//    )
//}