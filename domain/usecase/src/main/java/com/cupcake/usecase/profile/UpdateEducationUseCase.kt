package com.cupcake.usecase.profile

import com.cupcake.models.Education
import repo.JobFinderRepository
import javax.inject.Inject

class UpdateEducationUseCase @Inject constructor(
    val repository: JobFinderRepository
) {
    suspend operator fun invoke(education: Education){
        return repository.updateEducation(education)
    }
}