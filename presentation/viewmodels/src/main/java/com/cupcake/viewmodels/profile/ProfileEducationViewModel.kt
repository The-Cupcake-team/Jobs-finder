package com.cupcake.viewmodels.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.AddEducationUseCase
import com.cupcake.usecase.GetEducationUseCase
import com.cupcake.usecase.UpdateEducationUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEducationViewModel @Inject constructor(
    val getEducation: GetEducationUseCase,
    val addEducation: AddEducationUseCase,
    val updateEducation: UpdateEducationUseCase
) : BaseViewModel<EducationUiState>(EducationUiState()) {


    fun updateMode(isAdded: Boolean, id: String) {
        if (!isAdded){
            _state.update { it.copy(id = id, isAddState = false, title = "Edit Education") }
            getEducationById(id)
        }
    }

    private fun addEducation() {
        viewModelScope.launch {
            addEducation(_state.value.toEducation())
        }
        Log.v("hassan","add  ${_state.value}")
    }

    private fun updateEducation() {
        viewModelScope.launch {
            updateEducation(_state.value.toEducation())
        }
        Log.v("hassan","updated  ${_state.value}")
    }

    private fun getEducationById(id: String){
        tryToExecute(
            { getEducation("1").toEducationUiState() },
            ::onGetEducationSuccess,
            ::onGetEducationFailure
        )
    }

    private fun onGetEducationSuccess(educationUiState: EducationUiState) {
        _state.update { it.copy(education = educationUiState.education,
        school = educationUiState.school,
        city = educationUiState.city,
        startDate = educationUiState.startDate,
        endDate = educationUiState.endDate)
        }
    }

    private fun onGetEducationFailure(error: BaseErrorUiState) {
        _state.update { it.copy(error = error) }
    }


    fun onButtonSaveClicked() {
        if (_state.value.isAddState) {
            addEducation()
        } else {
            updateEducation()
        }
    }
}