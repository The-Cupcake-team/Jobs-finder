package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.domain.mapper.toJobTitle
import com.cupcake.jobsfinder.domain.reposirory.Repository
import javax.inject.Inject

class GetAllJobTitleUseCase @Inject constructor(
    private val jobTitleRepository: Repository,
) {
    suspend operator fun invoke() = jobTitleRepository.getAllJobTitles()
        .map { jobTitleDto -> jobTitleDto.toJobTitle() }
}
