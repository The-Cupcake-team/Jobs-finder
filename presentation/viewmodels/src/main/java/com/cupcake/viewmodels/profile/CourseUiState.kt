package com.cupcake.viewmodels.profile

import android.os.Parcelable
import com.cupcake.models.Course
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseUiState(
    val id: String = "",
    val title: String = "Add Course",
    val isAddState: Boolean = true,
    var name: String = "",
    var certificateLink: String = "",
    var description: String = "",
    var startDate: String = "",
    var endDate: String = "" ,
): Parcelable


fun Course.toCourseUiState(): CourseUiState {
    return CourseUiState(
        id = this.id ?: "",
        name = this.name ?: "",
        certificateLink = this.certificateLink ?: "",
        description = this.description ?: "",
        startDate = this.startDate ?: "",
        endDate = this.endDate ?: ""
    )
}

fun CourseUiState.toCourse(): Course {
    return Course(
        id = this.id ?: "",
        name = this.name ?: "",
        certificateLink = this.certificateLink ?: "",
        description = this.description ?: "",
        startDate = this.startDate ?: "",
        endDate = this.endDate ?: ""
    )
}
