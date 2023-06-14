package com.cupcake.viewmodels.create_job

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cupcake.models.Job
import com.cupcake.usecase.CreateJobUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CreateJobViewModel @Inject constructor(
    private val createJob: CreateJobUseCase
) : BaseViewModel<CreateJobUiState>(CreateJobUiState()) {

    private val _event = MutableLiveData<CreateJobEvent>()
    val event: LiveData<CreateJobEvent> = _event
    fun createJob() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            {
                createJob(
                    Job(
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
        Pair("Software Engineer", 0),
        Pair("Android Engineer", 1)
    )

    private fun onCreateJobSuccess(result: Boolean) {
        _state.update { it.copy(isLoading = false) }
        // more logic
    }

    private fun onCreateJobError(error: Exception) {
        _state.update {
            it.copy(
                isLoading = false,
                error = error.message.toString(),
            )
        }
    }

    fun handleEvent(event: CreateJobEvent) {
        when (event) {
            is CreateJobEvent.PageScrolled -> {
                onChangeIndexViewPager(event.index)
            }

        }
    }

    private fun onChangeIndexViewPager(index: Int) {
        _state.update {
            it.copy(
//                activeIconToolBar = getIconToolBar(index),
//                formNumber = getFormNumber(index),
//                titleToolBar = getTitleToolBar(index)
            )
        }
    }


    fun onSelectJopType(jobChipSelected: JobChipType) {
        when (jobChipSelected) {
            JobChipType.ON_SITE -> _state.update {
                it.copy(
                    jobType = "on-site"
                )
            }

            JobChipType.HYBRID -> _state.update {
                it.copy(
                    jobType = "Hybrid"
                )
            }

            JobChipType.REMOTE -> _state.update {
                it.copy(
                    jobType = "Remote"
                )
            }
        }
    }

    fun onSelectWorkType(workChipSelected: WorkChipType) {
        when (workChipSelected) {
            WorkChipType.FULL_TIME -> _state.update {
                it.copy(
                    workType = "Full-Time"
                )
            }

            WorkChipType.PART_TIME -> _state.update {
                it.copy(
                    workType = "Part-Time"
                )
            }

            WorkChipType.INTERNSHIP -> _state.update {
                it.copy(
                    workType = "Internship"
                )
            }

            WorkChipType.VOLUNTEER -> _state.update {
                it.copy(
                    workType = "Volunteer"
                )
            }

            WorkChipType.TEMPORARY -> _state.update {
                it.copy(
                    workType = "Temporary"
                )
            }

        }
    }

    fun onSelectExperienceType(experienceChipSelected: ExperienceChipType) {
        when (experienceChipSelected) {
            ExperienceChipType.ENTRY -> _state.update {
                it.copy(
                    experience = "Entry"
                )
            }

            ExperienceChipType.INTERMEDIATE -> _state.update {
                it.copy(
                    experience = "Intermediate"
                )
            }

            ExperienceChipType.SENIOR_LEVEL -> _state.update {
                it.copy(
                    experience = "Senior-level"
                )
            }

            ExperienceChipType.MID_LEVEL -> _state.update {
                it.copy(
                    experience = "Mid-level"
                )
            }

            ExperienceChipType.EXPERT -> _state.update {
                it.copy(
                    experience = "Expert"
                )
            }
        }
    }


}