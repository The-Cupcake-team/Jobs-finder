package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.domain.mapper.toJobTitle
import com.cupcake.jobsfinder.data.repository.JobFinderRepository
import javax.inject.Inject

class GetAllJobTitleUseCase @Inject constructor(
    private val jobTitleRepository: JobFinderRepository,
) {
    suspend operator fun invoke() = jobTitleRepository.getAllJobTitles()
        .map { jobTitleDto -> jobTitleDto.toJobTitle() }
}
