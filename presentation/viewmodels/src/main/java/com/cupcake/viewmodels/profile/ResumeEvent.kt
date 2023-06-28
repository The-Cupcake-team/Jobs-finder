package com.cupcake.viewmodels.profile


sealed class ResumeEvent{
     object DeleteSkill : ResumeEvent()
    data class EditeEducation(val education: EducationUiState, val fromAddButton: Boolean=false) : ResumeEvent()

}
