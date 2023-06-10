package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.data.repository.JobFinderRepository
import javax.inject.Inject

class CreateJobUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(paramCreateJob: ParamJobInfo): Boolean {
        return repository.createJob(paramCreateJob.toJobDto())
    }

    data class ParamJobInfo(
        val jobTitleId: Int,
        val company: String,
        val workType: String,
        val jobType: String,
        val jobLocation: String,
        val jobDescription: String,
        val jobSalary: Double,
    )

    private fun ParamJobInfo.toJobDto(): JobDto {
        return JobDto(
            jobTitleId = jobTitleId,
            company = company,
            workType = workType,
            jobType = jobType,
            jobLocation = jobLocation,
            jobDescription = jobDescription,
            jobSalary = jobSalary,
        )
    }

}