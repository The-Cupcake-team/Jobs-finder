package com.cupcake.usecase

import com.cupcake.models.Education
import repo.JobFinderRepository
import javax.inject.Inject

class UpdateEducationUseCase @Inject constructor(
    val repository: JobFinderRepository
) {
    suspend operator fun invoke(education: Education){
        validateEducation(education)
        return repository.updateEducation(education)
    }

    private fun validateEducation(education: Education){
        validateField(education.degree)
        validateField(education.school)
        validateField(education.startDate)
        validateField(education.city)
        validateField(education.endDate)
    }

    private fun validateField(field: String?){
        if (!field.isNullOrEmpty()){
            return
        }else{
            throw Throwable("Fields can not be empty")
        }
    }
}