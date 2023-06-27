package com.cupcake.usecase

import com.cupcake.models.Course
import repo.JobFinderRepository
import javax.inject.Inject

class UpdateCourseUseCase @Inject constructor(
    val repository: JobFinderRepository
) {
    suspend operator fun invoke(course: Course){
        validateCourse(course)
        return
    }

    private fun validateCourse(course: Course){
        validateField(course.name)
        validateField(course.description)
        validateField(course.certificateLink)
        validateField(course.startDate)
        validateField(course.endDate)
    }

    private fun validateField(field: String?){
        if (!field.isNullOrEmpty()){
            return
        }else{
            throw Throwable("Fields can not be empty")
        }
    }


}