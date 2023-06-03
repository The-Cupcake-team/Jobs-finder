package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.repository.Repository
import com.cupcake.jobsfinder.domain.mapper.toJobTitle
import com.cupcake.jobsfinder.domain.model.JobTitle
import javax.inject.Inject

class GetAllJobTitleUseCase @Inject constructor(
    private val jobTitleRepository: Repository,
) {
    suspend operator fun invoke(): List<JobTitle> {
        return jobTitleRepository.getAllJobTitles().map { jobTitleDto ->
            jobTitleDto.toJobTitle()
        }
    }
}