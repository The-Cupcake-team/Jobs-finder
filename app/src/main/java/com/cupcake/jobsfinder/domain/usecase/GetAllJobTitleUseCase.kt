package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.repository.Repository
import com.cupcake.jobsfinder.domain.mapper.toJobTitle
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllJobTitleUseCase @Inject constructor(
    private val jobTitleRepository: Repository,
) {
    suspend operator fun invoke()
    =  jobTitleRepository.getAllJobTitles()
        .map { listOfJobTitleDto -> listOfJobTitleDto.map { jobTitleDto ->
            jobTitleDto.toJobTitle() } }

    }
