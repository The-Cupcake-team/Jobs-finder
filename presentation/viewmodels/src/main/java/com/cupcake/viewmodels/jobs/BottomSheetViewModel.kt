package com.cupcake.viewmodels.jobs

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cupcake.models.Job
import com.cupcake.models.JobSalary
import com.cupcake.models.JobTitle
import com.cupcake.usecase.SaveJobUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    private val saveJobUseCase: SaveJobUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<BottomSheetUiState>(BottomSheetUiState()), BottomSheetListener {

    private val _event = MutableSharedFlow<BottomSheetEvent>()
    val event = _event.asSharedFlow()
    var jobUiState: JobUiState = savedStateHandle["jobUiState"] ?: JobUiState()

    override fun onShareClickListener() {
        viewModelScope.launch { _event.emit(BottomSheetEvent.OnShareClickListener(jobUiState.id)) }
    }

    override fun onSaveListener() {
        viewModelScope.launch(Dispatchers.IO) {
            saveJobUseCase(jobUiState.toJob())
        }
        viewModelScope.launch { _event.emit(BottomSheetEvent.OnSaveListener) }
    }

     fun changeSavedState() {
        viewModelScope.launch(Dispatchers.IO) {
            saveJobUseCase.isAlreadyExist(jobUiState.id).apply {
                _state.update {
                    it.copy(isSaved = this, textSaved = if (this) "Saved" else "Save")
                }
            }

        }
    }

    fun JobUiState.toJob(): Job {
        return Job(
            id = id,
            jobTitle = JobTitle(title = title, id = 12213),
            company = companyName,
            createdAt = createdAt,
            workType = detailsChip[0],
            jobLocation = location,
            jobType = detailsChip[1],
            jobDescription = companyName,
            jobSalary = JobSalary(minSalary = salary.toDouble(), maxSalary = salary.toDouble()),
            jobExperience = jobExperience,
            education = education,
            skills = emptyList()
        )
    }

}