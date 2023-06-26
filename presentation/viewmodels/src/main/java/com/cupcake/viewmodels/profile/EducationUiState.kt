package com.cupcake.viewmodels.profile

import android.os.Parcelable
import com.cupcake.models.Education
import com.cupcake.viewmodels.base.BaseErrorUiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class EducationUiState(
    val id: String = "",
    val title: String = "Add Eduction",
    val isAddState: Boolean = true,
    var education: String = "",
    var school: String = "",
    var city: String = "",
    var startDate: String = "",
    var endDate: String = "" ,
): Parcelable


fun Education.toEducationUiState(): EducationUiState{
    return EducationUiState(
        id = this.id ?: "",
        education = this.education ?: "",
        city = this.city ?: "",
        school = this.school ?: "",
        startDate = this.startDate ?: "",
        endDate = this.endDate ?: ""
    )
}

fun EducationUiState.toEducation(): Education{
    return Education(
        id = this.id,
        education = this.education,
        city = this.city,
        school = this.school,
        startDate = this.startDate,
        endDate = this.endDate
    )
}
