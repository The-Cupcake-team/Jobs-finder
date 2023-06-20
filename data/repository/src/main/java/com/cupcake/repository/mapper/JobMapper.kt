package com.cupcake.repository.mapper

import com.cupcake.models.Job
import com.cupcake.remote.response.job.JobDto
import com.cupcake.models.JobTitle
import com.cupcake.models.JobWithTitle
import com.cupcake.remote.response.JobTitleDto
import com.cupcake.remote.response.job.JobWithTitleDto

fun JobWithTitleDto.toJobWithJobTitle(): JobWithTitle {
    return JobWithTitle(
        id = id,
        jobTitle = JobTitle(
            id = jobTitle.id.toString(),
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

//fun Job.toJobDto(): JobDto {
//    return JobDto(
//        jobTitleId = jobTitleId,
//        company = company,
//        workType = workType,
//        jobLocation = jobLocation,
//        jobDescription = jobDescription,
//        jobType = jobType,
//        jobSalary = jobSalary
//    )
//}

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

fun JobTitleDto.toJobTitle(): JobTitle {
    return JobTitle(
        id = this.id.toString(),
        title = this.title
    )
}

fun JobDto.toJob(): Job {
    return Job(
        id = this.id ?: "",
        jobTitle = this.jobTitle?.toJobTitle() ?: JobTitle("", ""),
        company = this.company ?: "",
        createdAt = this.createdAt ?: 0L,
        workType = this.workType ?: "",
        jobLocation = this.jobLocation ?: "",
        jobType = this.jobType ?: "",
        jobDescription = this.jobDescription ?: "",
        jobSalary = this.jobSalary?.maxSalary ?: 0.0,
        jobExperience = this.experience ?: ""
    )
}

