package com.cupcake.jobsfinder.domain.useCase

import com.cupcake.jobsfinder.data.remote.response.JobDto
import com.cupcake.jobsfinder.data.repository.RepositoryImpl
import javax.inject.Inject

class CreateJobUseCase @Inject constructor(
    private val repository: RepositoryImpl
) {
    suspend operator fun invoke(paramCreateJob: ParamJobInfo): Boolean {
        return repository.createJob(paramCreateJob.toJobDto())
    }

    data class ParamJobInfo(
        val idJobTitle: Int,
        val company: String,
        val workType: String,
        val jobLocation: String,
        val jobDescription: String,
        val price: String,
    )

    private fun ParamJobInfo.toJobDto(): JobDto {
        return JobDto(
            idJobTitle = idJobTitle,
            company = company,
            workType = workType,
            jobLocation = jobLocation,
            jobDescription = jobDescription,
            price = price,
        )
    }
}