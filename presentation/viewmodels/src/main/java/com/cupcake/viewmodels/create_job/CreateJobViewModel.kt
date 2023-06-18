package com.cupcake.viewmodels.create_job


import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.CreateJobUseCase
import com.cupcake.usecase.GetAllJobTitleUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.jobs.JobTitleUiState
import com.cupcake.viewmodels.jobs.toJobTitleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateJobViewModel @Inject constructor(
    private val createJob: CreateJobUseCase,
    private val jobTitles: GetAllJobTitleUseCase
) : BaseViewModel<CreateJobUiState>(CreateJobUiState()) {

    private val _event = MutableSharedFlow<CreateJobEvent>()
    val event = _event.asSharedFlow()

    private var searchJobTitle : Job? = null


//    fun createJob() {
//        tryToExecute(
//            {
//                createJob(
//                    Job(
//                        id = "",
//                        jobTitleId = jobTitleMap[_state.value.jobFormUiState.jobTitleId]!!,
//                        company = _state.value.jobFormUiState.company,
//                        workType = _state.value.jobFormUiState.workType,
//                        jobType = _state.value.jobFormUiState.jobType,
//                        jobLocation = _state.value.jobFormUiState.jobLocation,
//                        jobDescription = _state.value.jobFormUiState.jobDescription,
//                        jobSalary = _state.value.jobFormUiState.salary,
//                        createdAt = 1111111111
//                    )
//                )
//            },
//            ::onCreateJobSuccess,
//            ::onCreateJobError
//        )
//    }

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


    fun onSelectJopType(jobType: JobType) {
        _state.update { it.copy(jobFormUiState = it.jobFormUiState.copy(jobType = jobType.value)) }
    }

    fun onSelectWorkType(workType: WorkType) {
        _state.update { it.copy(jobFormUiState = it.jobFormUiState.copy(workType = workType.value)) }
    }

    fun onSelectExperienceType(experience: ExperienceType) {
        _state.update { it.copy(jobFormUiState = it.jobFormUiState.copy(experience = experience.value)) }
    }

    fun onDescriptionChange(text: CharSequence) {
        _state.update { it.copy(jobFormUiState = it.jobFormUiState.copy(jobDescription = text.toString())) }
    }

    fun onEducationChange(text: CharSequence) {
        _state.update { it.copy(jobFormUiState = it.jobFormUiState.copy(education = text.toString())) }
    }


    fun onJobTitleChange(query: CharSequence) {
        searchJobTitle?.cancel()
        searchJobTitle = viewModelScope.launch {
            delay(200)
            tryToExecute(
                { jobTitles(query.toString()).map { it.toJobTitleUiState() } },
                ::onGetJobTitleSuccess,
                ::onError,
            )
        }
    }


    private fun onGetJobTitleSuccess(jobTitles: List<JobTitleUiState>){
        _state.update { it.copy(isLoading = false, jobFormUiState = it.jobFormUiState.copy(jobTitles = jobTitles)) }
    }

    private fun onError(error: BaseErrorUiState) {
        _state.update { it.copy(isLoading = false, error = error) }
    }

    fun onExperienceRequirementChange(text: CharSequence) {
        _state.update { it.copy(jobFormUiState = it.jobFormUiState.copy(experienceRequirement = text.toString())) }
    }

    fun onSkillsChange(text: CharSequence) {
        _state.update { it.copy(jobFormUiState = it.jobFormUiState.copy()) }
    }


    fun onChangeRangSalary(values: List<Float>) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    startRangSalary = values.first().toInt().toString(),
                    endRangSalary = values.last().toInt().toString(),
                )
            )
        }
    }


}