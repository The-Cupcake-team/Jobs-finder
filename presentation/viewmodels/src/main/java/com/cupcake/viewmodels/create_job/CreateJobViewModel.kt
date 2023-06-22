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
) : BaseViewModel<CreateJobUiState>(CreateJobUiState()), CreateJobInteractionListener {

    private val _event = MutableSharedFlow<CreateJobEvent>()
    val event = _event.asSharedFlow()

    private var searchJobTitle: com.cupcake.models.Job? = null
    fun createJob() {
        _state.update { it.copy(isLoading = true) }
//        tryToExecute(
//            {
//                createJob(
////                    Job(
////                        id = "",
////                        jobTitle = JobTitle(_state.value.jobFormUiState.jobTitleUIState.id, _state.value.jobFormUiState.jobTitleUIState.title),
////                        company = _state.value.jobFormUiState.company,
////                        workType = _state.value.jobFormUiState.workType,
////                        jobType = _state.value.jobFormUiState.jobType,
////                        jobLocation = _state.value.jobFormUiState.jobLocation,
////                        jobDescription = _state.value.jobFormUiState.jobDescription,
////                        jobSalary = JobSalary(maxSalary = _state.value.jobFormUiState.salary.maxSalary, minSalary = _state.value.jobFormUiState.salary.minSalary),
////                        createdAt = 1111111111,
////                        jobExperience = _state.value.jobFormUiState.experience,
////                        education = _state.value.jobFormUiState.education
////                    )
//                )
//            },
//            ::onCreateJobSuccess,
//            ::onCreateJobError
//        )
    }


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
                onChangeIndexViewPager(event.index)
            }

            is CreateJobEvent.HeaderButtonClicked -> {
                onHeaderButtonClicked(event.index)
            }

        }
    }

    override fun onHeaderButtonClicked(index: Int) {
        viewModelScope.launch {
            _event.emit(CreateJobEvent.HeaderButtonClicked(index))
        }
    }

    override fun onNextClicked(state: Int) {
        viewModelScope.launch {
            _event.emit(CreateJobEvent.PageScrolled(state))
        }
    }

    fun onChangeIndexViewPager(index: Int) {
        viewModelScope.launch {
            _event.emit(CreateJobEvent.PageScrolled(index))
        }
        when (index) {
            PAGE_ONE -> _state.update { it.copy(buttonText = "Next", progressStep = 1) }
            PAGE_TWO -> _state.update { it.copy(buttonText = "Post", progressStep = 0) }
        }
    }

    fun onBack(index: Int) {
        viewModelScope.launch {

            if (index == 1) {
            } else {
                _event.emit(CreateJobEvent.PageScrolled(index))
            }

        }
        when (index) {
            PAGE_ONE -> _state.update { it.copy(buttonText = "Next", progressStep = 1) }
            PAGE_TWO -> _state.update { it.copy(buttonText = "Post", progressStep = 0) }


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

    fun onSkillsChange(skills: List<String>) {
        _state.update { it.copy(jobFormUiState = it.jobFormUiState.copy(
            skills = skills
        )) }
    }


    fun onChangeRangSalary(values: List<Float>) {
        _state.update {
            it.copy(
                jobFormUiState = it.jobFormUiState.copy(
                    experienceRequirement = text.toString()
                )
            )
        }
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

//    fun onSkillsChange(text: CharSequence) {
//        _state.update {
//            it.copy(
//                jobFormUiState = it.jobFormUiState.copy(
//                    skills = text.toString()
//                )
//            )
//        }
//    }

    fun onSkillsChange(skills: List<String>) {
        _state.update { it.copy(jobFormUiState = it.jobFormUiState.copy(
            skills = skills
        )) }
    }

    private companion object {
        const val PAGE_ONE = 0
        const val PAGE_TWO = 1
    }

}