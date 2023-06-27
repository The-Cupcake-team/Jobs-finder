package com.cupcake.viewmodels.profile.resume

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.EducationUseCase
import com.cupcake.usecase.UpdateEducationUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.profile.EducationUiState
import com.cupcake.viewmodels.profile.SaveEvent
import com.cupcake.viewmodels.profile.toEducation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEducationViewModel @Inject constructor(
    val addEducation: EducationUseCase,
    val updateEducation: UpdateEducationUseCase
) : BaseViewModel<EducationUiState>(EducationUiState()) {

    private val _event = MutableSharedFlow<SaveEvent>()
    val event = _event.asSharedFlow()

    fun updateMode(isAdded: Boolean, educationUiState: EducationUiState) {
        if (!isAdded){
            _state.update { educationUiState.copy(isAddState = false, title = "Edit Education") }
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

    private fun onError(error: BaseErrorUiState) {
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