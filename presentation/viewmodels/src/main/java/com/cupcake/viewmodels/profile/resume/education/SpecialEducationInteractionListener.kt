package com.cupcake.viewmodels.profile.resume.education

import com.cupcake.viewmodels.base.BaseInteractionListener
import com.cupcake.viewmodels.profile.EducationUiState

interface SpecialEducationInteractionListener: BaseInteractionListener {

    fun editeEducation(education:EducationUiState,fromAddButton:Boolean=false)
}