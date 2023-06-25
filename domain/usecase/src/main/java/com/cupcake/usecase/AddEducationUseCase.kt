package com.cupcake.usecase

import com.cupcake.models.Education
import repo.JobFinderRepository
import javax.inject.Inject

class AddEducationUseCase @Inject constructor(
    val repository: JobFinderRepository
) {
    suspend operator fun invoke(education: Education){
        return repository.addEducation(education)
    }
}