package com.cupcake.viewmodels.profile

import android.os.Parcelable
import com.cupcake.models.Education
import kotlinx.parcelize.Parcelize

@Parcelize
data class EducationUiState(
    val id: String = "",
    val title: String = "Add Eduction",
    val isAddState: Boolean = true,
    var degree: String = "",
    var school: String = "",
    var city: String = "",
    var startDate: String = "",
    var endDate: String = "" ,
): Parcelable


fun Education.toEducationUiState(): EducationUiState {
    return EducationUiState(
        id = this.id ?: "",
        degree = this.degree ?: "",
        city = this.city ?: "",
        school = this.school ?: "",
        startDate = this.startDate ?: "",
        endDate = this.endDate ?: ""
    )
}

fun EducationUiState.toEducation(): Education{
    return Education(
        id = this.id,
        degree = this.degree,
        city = this.city,
        school = this.school,
        startDate = this.startDate,
        endDate = this.endDate,
        )
}
