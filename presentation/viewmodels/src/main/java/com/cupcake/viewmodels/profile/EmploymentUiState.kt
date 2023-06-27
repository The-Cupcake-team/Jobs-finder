package com.cupcake.viewmodels.profile

import android.os.Parcelable
import com.cupcake.models.Employment
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmploymentUiState(
    val id: String = "",
    val title: String = "Add Employment",
    val isAddState: Boolean = true,
    var position: String = "",
    var company: String = "",
    var startDate: String = "",
    var endDate: String = "" ,
): Parcelable


fun Employment.toEmploymentUiState(): EmploymentUiState {
    return EmploymentUiState(
        id = this.id ?: "",
        position = this.position ?: "",
        company = this.company ?: "",
        startDate = this.startDate ?: "",
        endDate = this.endDate ?: ""
    )
}

fun EmploymentUiState.toEmployment(): Employment {
    return Employment(
        id = this.id,
        position = this.position,
        company = this.company,
        startDate = this.startDate,
        endDate = this.endDate
    )
}
