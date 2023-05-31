package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.dto.JobTitleDto
import com.cupcake.jobsfinder.data.repository.Repository
import com.cupcake.jobsfinder.domain.mapper.JobTitleMapper
import com.cupcake.jobsfinder.domain.model.JobTitle
import javax.inject.Inject

class GetAllJobTitleUseCase @Inject constructor(
    private val jobTitleRepository: Repository,
    private val jobTitleMapper: JobTitleMapper
) {
    suspend operator fun invoke() : List<JobTitle>{
        return jobTitleRepository.getAllJobTitles().map { jobTitle ->
            jobTitleMapper.mapTo(jobTitle)
        }
    }
}