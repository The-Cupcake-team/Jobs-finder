package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.repository.Repository
import javax.inject.Inject

class GetAllJobUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(limit: Int) = repository.getAllJobs()
}