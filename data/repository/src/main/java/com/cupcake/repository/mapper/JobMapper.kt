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
        createdAt = createdAt ?: 0L,
        workType = workType ?: "",
        jobLocation = jobLocation ?: "",
        jobDescription = jobDescription ?: "",
        jobType = jobType ?: "",
        jobSalary = JobSalary(minSalary = jobSalary?.minSalary ?: 0.0, maxSalary = jobSalary?.maxSalary ?: 0.0),
        jobExperience = experience ?: "",
        education = eduction ?: ""
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

//fun JobTitleDto.toJobTitle(): JobTitle {
//    return JobTitle(
//        id = this.id.toString(),
//        title = this.title
//    )
//}

fun JobDto.toJob(): Job {
    return Job(
        id = this.id ?: "",
        jobTitle = this.jobTitle?.toJobTitle() ?: JobTitle(
            jobTitle?.id ?: -1,
            jobTitle?.title ?: ""
        ),
        company = this.company ?: "",
        createdAt = this.createdAt ?: 0L,
        workType = this.workType ?: "",
        jobLocation = this.jobLocation ?: "",
        jobType = this.jobType ?: "",
        jobDescription = this.jobDescription ?: "",
        jobSalary = JobSalary(
            maxSalary = jobSalary?.maxSalary ?: 0.0,
            minSalary = jobSalary?.minSalary ?: 0.0
        ),
        jobExperience = this.experience ?: "",
        education = this.eduction ?: ""
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
        education = education
    )
}

