package com.cupcake.jobsfinder.domain.useCase

import com.cupcake.jobsfinder.domain.reposirory.Repository
import com.cupcake.jobsfinder.domain.mapper.toJob
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class GetAllJobUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(limit: Int) = repository.getAllJobs().take(limit).map {jobsDto ->
        jobsDto.map { it.toJob() }
    }
}