package com.cupcake.viewmodels.create_job


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.models.Job
import com.cupcake.models.JobSalary
import com.cupcake.models.JobTitle
import com.cupcake.usecase.CreateJobUseCase
import com.cupcake.usecase.GetAllJobTitleUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.jobs.JobTitleUiState
import com.cupcake.viewmodels.jobs.toJobTitleUiState
import com.cupcake.viewmodels.utill.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateJobViewModel @Inject constructor(
    private val createJob: CreateJobUseCase,
    private val jobTitles: GetAllJobTitleUseCase,
) : BaseViewModel<CreateJobUiState>(CreateJobUiState()), CreateJobInteractionListener {

    private val _event = MutableSharedFlow<Event<CreateJobEvent>>()
    val event = _event.asSharedFlow()

    private var searchJobTitle: kotlinx.coroutines.Job? = null
    fun createJob() {
        _state.update { it.copy(isLoading = true) }
        tryToExecuteDebounced(
            {
                createJob(
                    Job(
                        id = "",
                        jobTitle = JobTitle(
                            id = state.value.jobFormUiState.jobTitleUIState.id,
                            title = state.value.jobFormUiState.jobTitleUIState.title
                        ),
                        company = _state.value.jobFormUiState.company,
                        workType = _state.value.jobFormUiState.workType,
                        jobType = _state.value.jobFormUiState.jobType,
                        jobLocation = _state.value.jobFormUiState.jobLocation,
                        jobDescription = _state.value.jobFormUiState.jobDescription,
                        jobSalary = JobSalary(
                            maxSalary = _state.value.jobFormUiState.salary.maxSalary,
                            minSalary = _state.value.jobFormUiState.salary.minSalary
                        ),
                        createdAt = "",
                        jobExperience = _state.value.jobFormUiState.experience,
                        education = _state.value.jobFormUiState.education,
                        skills = _state.value.jobFormUiState.skills
                    )
                )
            },
            ::onCreateJobSuccess,
            ::onCreateJobError
        )
    }

    private fun onCreateJobSuccess(result: Boolean) {
        _state.update { it.copy(isLoading = false, error = null) }
        viewModelScope.launch {
            _event.emit(Event(CreateJobEvent.JobCreated))
        }
    }

    private fun onCreateJobError(error: BaseErrorUiState) {
        _state.update {
            it.copy(
                isLoading = false,
                error = error,
            )
        }
        viewModelScope.launch {
            _event.emit(Event(CreateJobEvent.ShowError(error.errorCode)))
        }
    }


    override fun onNextClicked() {
        viewModelScope.launch {
            _event.emit(Event(CreateJobEvent.NextPage))
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


    fun onJobTitleChange(query: CharSequence) {
        searchJobTitle?.cancel()
        searchJobTitle = viewModelScope.launch {
            tryToExecute(
                { jobTitles(query.toString()).map { it.toJobTitleUiState() } },
                ::onGetJobTitleSuccess,
                ::onError,
            )
        }
    }


    private fun onGetJobTitleSuccess(jobTitles: List<JobTitleUiState>) {
        _state.update {
            it.copy(
                isLoading = false,
                jobFormUiState = it.jobFormUiState.copy(
                    jobTitles = jobTitles,
                    jobTitleUIState = jobTitles.firstOrNull()!!
                )
            )
        }
    }

    private fun onError(error: BaseErrorUiState) {
        _state.update { it.copy(isLoading = false, error = error) }
    }

    fun onExperienceRequirementChange(text: CharSequence) {
        _state.update { it.copy(jobFormUiState = it.jobFormUiState.copy(experienceRequirement = text.toString())) }
    }

    fun onSkillsChange(skills: CharSequence) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    skills = listOf(skills.toString())
                )
            )
        }
    }

    fun onCompanyChange(text: CharSequence) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    company = text.toString()
                )
            )
        }
    }

    fun onLocationChange(text: CharSequence) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    jobLocation = text.toString()
                )
            )
        }
    }

    fun onChangeRangSalary(values: List<Float>) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    salary = it.jobFormUiState.salary.copy(
                        minSalary = values.first().toDouble(),
                        maxSalary = values.last().toDouble()
                    )
                )
            )
        }
    }


}