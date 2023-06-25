package com.cupcake.usecase

import com.cupcake.models.Education
import repo.JobFinderRepository
import javax.inject.Inject

class GetEducationUseCase @Inject constructor(
    val repository: JobFinderRepository
) {
    suspend operator fun invoke(id: String): Education{
        return repository.getEducation(id)
    }
}