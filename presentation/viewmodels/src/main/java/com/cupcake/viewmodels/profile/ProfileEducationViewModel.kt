package com.cupcake.viewmodels.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.AddEducationUseCase
import com.cupcake.usecase.GetEducationUseCase
import com.cupcake.usecase.UpdateEducationUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEducationViewModel @Inject constructor(
    val getEducation: GetEducationUseCase,
    val addEducation: AddEducationUseCase,
    val updateEducation: UpdateEducationUseCase
) : BaseViewModel<EducationUiState>(EducationUiState()) {

    private val _event = MutableSharedFlow<SaveEvent>()
    val event = _event.asSharedFlow()

    fun updateMode(isAdded: Boolean, id: String) {
        if (!isAdded){
            _state.update { it.copy(id = id, isAddState = false, title = "Edit Education") }
            getEducationById(id)
        }
    }

    private fun addEducation() {
       tryToExecute(
           {addEducation(_state.value.toEducation())},
           ::onAddEducationSuccess,
           ::onError
       )
    }

    private fun onAddEducationSuccess(unit: Unit) {
        viewModelScope.launch {
            _event.emit(SaveEvent.Added)
        }
    }

    private fun onUpdateEducationSuccess(unit: Unit) {
        viewModelScope.launch {
            _event.emit(SaveEvent.Updated)
        }
    }

    private fun updateEducation() {
        tryToExecute(
            {addEducation(_state.value.toEducation())},
            ::onAddEducationSuccess,
            ::onError
        )
    }

    private fun getEducationById(id: String){
        tryToExecute(
            { getEducation("1").toEducationUiState() },
            ::onGetEducationSuccess,
            ::onError
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


    private fun onError(error: BaseErrorUiState) {
        _state.update { it.copy(error = error) }
        viewModelScope.launch {
            _event.emit(SaveEvent.Error)
        }
    }


    fun onButtonSaveClicked() {
        if (_state.value.isAddState) {
            addEducation()
        } else {
            updateEducation()
        }
    }
}