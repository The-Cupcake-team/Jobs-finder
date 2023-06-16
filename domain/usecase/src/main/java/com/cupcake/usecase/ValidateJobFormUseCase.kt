package com.cupcake.usecase

import com.cupcake.models.Job
import javax.inject.Inject

class ValidateJobFormUseCase @Inject constructor() {
    operator fun invoke(jobInfo: Job) {
        validateField(jobInfo.jobLocation)
        validateField(jobInfo.company)
        validateField(jobInfo.jobType)
        validateField(jobInfo.workType)
    }

    private fun validateField(field: String) {
        if (field.isBlank() || field.isEmpty()) {
            throw Throwable("This field is required")
        }
        val regex = Regex("\\d")
        if (regex.containsMatchIn(field)) {
            throw Throwable("This field shouldn't contain only numbers")
        }
    }
}