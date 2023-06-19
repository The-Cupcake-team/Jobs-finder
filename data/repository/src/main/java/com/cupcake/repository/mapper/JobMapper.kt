package com.cupcake.repository.mapper

import com.cupcake.jobsfinder.local.entities.JobsEntity
import com.cupcake.models.Job
import com.cupcake.remote.response.job.JobDto
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

fun JobWithTitle.toJobsEntity(): JobsEntity{
    return JobsEntity(
        id = id,
        jobId = jobTitle.id,
        jobTitle = jobTitle.title,
        company = company,
        createdTime = createdTime,
        workType = workType,
        jobLocation = jobLocation,
        jobType = jobType,
        jobDescription = jobDescription,
        salary = salary

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

fun JobsEntity.toJobWithTitle(): JobWithTitle{
    return return JobWithTitle(
        id = id,
        jobTitle = JobTitle(
            id = jobId,
            title = jobTitle
        ),
        company = company,
        createdTime = createdTime,
        workType = workType,
        jobLocation = jobLocation,
        jobDescription = jobDescription,
        jobType = jobType,
        salary = salary
    )
}

