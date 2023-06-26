package com.cupcake.usecase.profile

import com.cupcake.models.Education
import repo.JobFinderRepository
import javax.inject.Inject

class AddEducationUseCase @Inject constructor(
    val repository: JobFinderRepository
) {
    suspend operator fun invoke(education: Education){
        validateEducation(education)
        return repository.addEducation(education)
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