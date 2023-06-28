package com.cupcake.viewmodels.profile

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.AddCourseUseCase
import com.cupcake.usecase.UpdateCourseUseCase
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
class ProfileCourseViewModel @Inject constructor(
    val addCourse: AddCourseUseCase,
    val updateCourse: UpdateCourseUseCase
) : BaseViewModel<CourseUiState>(CourseUiState()) {

    private val _event = MutableSharedFlow<SaveEvent>()
    val event = _event.asSharedFlow()

    fun updateMode(isAdded: Boolean, courseUiState: CourseUiState) {
        if (!isAdded) {
            _state.update { courseUiState.copy(isAddState = false, title = "Edit Course") }
        }else{
            _state.update { courseUiState.copy(isAddState = true, title = "Add Course") }
        }
    }

    private fun addCourseToDB() {
        tryToExecute(
            { addCourse(_state.value.toCourse()) },
            ::onSavedSuccess,
            ::onError
        )
    }

    private fun updateCourseInDB() {
        tryToExecute(
            { updateCourse(_state.value.toCourse()) },
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
            addCourseToDB()
        } else {
            updateCourseInDB()
        }
    }
}