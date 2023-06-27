package com.cupcake.viewmodels.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.models.Education
import com.cupcake.models.Skill
import com.cupcake.usecase.ResumeUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.profile.resume.education.SpecialEducationInteractionListener
import com.cupcake.viewmodels.profile.resume.skill.SpecialSkillsInteractionListener
import com.cupcake.viewmodels.profile.resume.skill.toSkillsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProfileResumeViewModel @Inject constructor(
    private val resumeUseCase: ResumeUseCase,
) : BaseViewModel<ResumeUiState>(ResumeUiState()),
    SpecialEducationInteractionListener, SpecialSkillsInteractionListener {

    private val _event = MutableSharedFlow<ResumeEvent>()
    val event = _event.asSharedFlow()
    init {
        getAllEducation()
        getAllSkills()







    }
    private fun getAllEducation(){
        tryToExecute(
            { resumeUseCase.getAllEducations()},
            ::onGetEducationsSuccess,
            ::onError
        )
    }
    private fun onGetEducationsSuccess(education : List<Education>) {
        _state.update {
            it.copy(
             educationUiState = education.map { education -> education.toEducationUiState() })
        }

    }




    private fun onDeleteSkillsSuccess(id: Unit) {}
    private fun onGetSkillsSuccess(skill : List<Skill>) {
        _state.update {
            it.copy(
            sillsUiState  = skill.map { skill -> skill.toSkillsUiState() })
        }
        Log.d("TAG", "${skill.size}asdasdasdasd")

    }
    private fun getAllSkills(){
        tryToExecute(
            { resumeUseCase.getAllSkills()},
            ::onGetSkillsSuccess,
            ::onError
        )
    }
    override fun deleteSkill(id: String) {
        tryToExecute(
            { resumeUseCase.deleteSkills(id)},
            ::onDeleteSkillsSuccess,
            ::onError
        )
        getAllSkills()
        viewModelScope.launch (Dispatchers.IO){
            _event.emit(ResumeEvent.DeleteSkill)
        }
    }



    private fun onError(error: BaseErrorUiState) {
        Log.d("TAG", "resumeUseCaseError: ${error.errorCode}")
    }


    override  fun editeEducation(education: EducationUiState, fromAddButton: Boolean) {
        viewModelScope.launch (Dispatchers.IO){
           _event.emit(ResumeEvent.EditeEducation(education))

        }
    }
}


