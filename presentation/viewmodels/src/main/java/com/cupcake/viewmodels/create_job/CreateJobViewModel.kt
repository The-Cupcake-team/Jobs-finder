package com.cupcake.viewmodels.create_job

import androidx.lifecycle.viewModelScope
import com.cupcake.models.Job
import com.cupcake.usecase.CreateJobUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateJobViewModel @Inject constructor(
    private val createJob: CreateJobUseCase
) : BaseViewModel<CreateJobUiState>(CreateJobUiState()) {

    private val _event = MutableSharedFlow<CreateJobEvent>()
    val event = _event.asSharedFlow()

    fun createJob() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            {
                createJob(
                    Job(
                        id = "",
                        jobTitleId = jobTitleMap[_state.value.jobFormUiState.jobTitleId]!!,
                        company = _state.value.jobFormUiState.company,
                        workType = _state.value.jobFormUiState.workType,
                        jobType = _state.value.jobFormUiState.jobType,
                        jobLocation = _state.value.jobFormUiState.jobLocation,
                        jobDescription = _state.value.jobFormUiState.jobDescription,
                        jobSalary = _state.value.jobFormUiState.salary,
                        createdAt = 1111111111
                    )
                )
            },
            ::onCreateJobSuccess,
            ::onCreateJobError
        )
    }

    private val jobTitleMap = hashMapOf(
        Pair("Android", 1),
        Pair("backend developer", 2)
    )

    private fun onCreateJobSuccess(result: Boolean) {
        _state.update { it.copy(isLoading = false) }
        // more logic
    }

    private fun onCreateJobError(error: BaseErrorUiState) {
        _state.update {
            it.copy(
                isLoading = false,
                error = error,
            )
        }
    }


    fun handleEvent(event: CreateJobEvent) {
        when (event) {
            is CreateJobEvent.PageScrolled -> {
                onChangeIndexViewPager(event.index)
            }

            is CreateJobEvent.HeaderButtonClicked -> {
                onHeaderButtonClicked(event.index)
            }
        }
    }

    private fun onHeaderButtonClicked(index: Int) {
        viewModelScope.launch {
            _event.emit(CreateJobEvent.HeaderButtonClicked(index))
        }
    }

    private fun onChangeIndexViewPager(index: Int) {
        when (index) {
            0 -> {
                _state.update {
                    it.copy(
                        formNumber = "1 of 2",
                        buttonText = "Next"
                    )
                }
            }

            1 -> {
                _state.update {
                    it.copy(
                        formNumber = "2 of 2",
                        buttonText = "Post"
                    )
                }
            }
        }
    }


    fun onSelectJopType(jobType: JobType) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    jobType = jobType.value
                )
            )
        }
    }

    fun onSelectWorkType(workType: WorkType) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    workType = workType.value
                )
            )
        }
    }

    fun onSelectExperienceType(experience: ExperienceType) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    experience = experience.value
                )
            )
        }
    }

    fun onDescriptionChange(text: CharSequence) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    jobDescription = text.toString()
                )
            )
        }
    }

    fun onEducationChange(text: CharSequence) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    education = text.toString()
                )
            )
        }
    }

    fun onExperienceRequirementChange(text: CharSequence) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    experienceRequirement = text.toString()
                )
            )
        }
    }
    fun onSkillsChange(text: CharSequence) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    skills = text.toString()
                )
            )
        }
    }

}