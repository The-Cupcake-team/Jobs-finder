package com.cupcake.usecase

import com.cupcake.models.Education
import com.cupcake.models.Employment
import repo.JobFinderRepository
import javax.inject.Inject

class UpdateEmploymentUseCase @Inject constructor(
    val repository: JobFinderRepository
) {
    suspend operator fun invoke(employment: Employment){
        validateEmployment(employment)
        return
    }

    private fun validateEmployment(employment: Employment){
        validateField(employment.company)
        validateField(employment.position)
        validateField(employment.startDate)
        validateField(employment.endDate)
    }

    private fun validateField(field: String?){
        if (!field.isNullOrEmpty()){
            return
        }else{
            throw Throwable("Fields can not be empty")
        }
    }


}