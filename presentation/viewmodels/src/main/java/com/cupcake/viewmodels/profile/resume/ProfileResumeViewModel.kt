package com.cupcake.viewmodels.profile.resume

import android.util.Log
import com.cupcake.models.Education
import com.cupcake.models.Skill
import com.cupcake.usecase.ResumeUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.profile.toEducationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject
@HiltViewModel
class ProfileResumeViewModel @Inject constructor(
    private val resumeUseCase: ResumeUseCase,
) : BaseViewModel<ResumeUiState>(ResumeUiState()),
    SpecialEducationInteractionListener,SpecialSkillsInteractionListener {
    init {
        tryToExecute(
            { resumeUseCase.getAllEducations()},
            ::onSavedEducationsSuccess,
            ::onError
        )

        tryToExecute(
            { resumeUseCase.getAllSkills()},
            ::onSavedSkillsSuccess,
            ::onError
        )
    }
    private fun onSavedEducationsSuccess(education : List<Education>) {
        _state.update {
            it.copy(
             educationUiState = education.map { education -> education.toEducationUiState() })
        }
    }
    private fun onSavedSkillsSuccess(skill : List<Skill>) {
        _state.update {
            it.copy(
            sillsUiState  = skill.map { skill -> skill.toSkillsUiState() })
        }
    }

    private fun onError(error: BaseErrorUiState) {
        Log.d("TAG", "resumeUseCaseError: ${error.errorCode}")
    }
}


