package com.cupcake.jobsfinder.ui.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.usecase.GetAllJobUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import com.cupcake.jobsfinder.ui.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val getAllJobUseCase: GetAllJobUseCase
) : BaseViewModel() {

    private val _jobsUIState = MutableStateFlow(JobsUiState())
    val jobsUIState: StateFlow<JobsUiState> = _jobsUIState

    private val errors: MutableList<ErrorUiState> = mutableListOf()

    init {
        viewModelScope.launch {
            getJobRecommended()
        }
    }

    private suspend fun getJobRecommended() {
        _jobsUIState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getAllJobUseCase(10)
                .catch { throwable ->
                    errors.add(ErrorUiState(0, throwable.message.toString()))
                    _jobsUIState.update { it.copy(error = errors) }
                }
                .collect { jobs ->
                    val recommendedJobs = jobs.map { job ->
                        JobUiState(
                            image = "",
                            title = "",
                            companyName = job.company ?: "",
                            detailsChip = listOf(job.workType.toString(), job.jobType.toString()),
                            location = job.jobLocation ?: "",
                            salary = job.salary ?: ""
                        )
                    }

                    _jobsUIState.update { it.copy(job = recommendedJobs, isLoading = false) }
                }
        }
    }
}