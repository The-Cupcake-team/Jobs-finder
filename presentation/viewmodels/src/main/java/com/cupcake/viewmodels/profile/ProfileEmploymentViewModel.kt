package com.cupcake.viewmodels.profile

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.AddEmploymentUseCase
import com.cupcake.usecase.UpdateEmploymentUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.profile.resume.education.SaveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEmploymentViewModel @Inject constructor(
   val  addEmployment: AddEmploymentUseCase,
   val  updateEmployment: UpdateEmploymentUseCase
): BaseViewModel<EmploymentUiState>(EmploymentUiState()) {

    private val _event = MutableSharedFlow<SaveEvent>()
    val event = _event.asSharedFlow()

    fun updateMode(isAdded: Boolean, employmentUiState: EmploymentUiState) {
        if (!isAdded){
            _state.update { employmentUiState.copy(isAddState = false, title = "Edit Employment") }
        }else{
            _state.update { employmentUiState.copy(isAddState = true, title = "Add Employment") }
        }
    }


    private fun addEmploymentToDB() {
        tryToExecute(
            {addEmployment(_state.value.toEmployment())},
            ::onSavedSuccess,
            ::onError
        )
    }

    private fun updateEmploymentToDB() {
        tryToExecute(
            {updateEmployment(_state.value.toEmployment())},
            ::onSavedSuccess,
            ::onError
        )
    }

    private fun onSavedSuccess(unit: Unit) {
        viewModelScope.launch {
            _event.emit(SaveEvent.Added)
        }
    }

    private fun onError(error: BaseErrorUiState) {
        viewModelScope.launch {
            _event.emit(SaveEvent.Error)
        }
    }


    fun onButtonSaveClicked() {
        if (_state.value.isAddState) {
            addEmploymentToDB()
        } else {
            updateEmploymentToDB()
        }
    }
}