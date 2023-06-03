package com.cupcake.jobsfinder.domain.useCase

import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.data.repository.Repository
import javax.inject.Inject

class CreateJobUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(paramCreateJob: ParamJobInfo): Boolean {
        return repository.createJob(paramCreateJob.toJobDto())
    }

    data class ParamJobInfo(
        val idJobTitle: Long,
        val company: String,
        val workType: String,
        val jobType: String,
        val jobLocation: String,
        val jobDescription: String,
        val jobSalary: String,
    )

    private fun ParamJobInfo.toJobDto(): JobDto {
        return JobDto(
            jobTitleId = idJobTitle,
            company = company,
            workType = workType,
            jobType = jobType,
            jobLocation = jobLocation,
            jobDescription = jobDescription,
            jobSalary = jobSalary,
        )
    }
}