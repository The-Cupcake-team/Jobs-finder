package com.cupcake.jobsfinder.ui.jobs


import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.usecase.GetJobByIdUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val getJobByIdUseCase: GetJobByIdUseCase
) : BaseViewModel() {

    private val _jobsUIState = MutableStateFlow(JobsUiState())
    val jobsUIState: StateFlow<JobsUiState> = _jobsUIState

    private val errors: MutableList<ErrorUiState> = mutableListOf()

    init {
        getJobById(1)
    }

    private fun getJobById(jobId: Int) {
        _jobsUIState.value = _jobsUIState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val job = getJobByIdUseCase(jobId)
                val jobUiState = JobUiState(
                    image = "",
                    title = job.jobTitleId.toString(),
                    companyName = job.company ?: "",
                    detailsChip = listOf(job.workType.toString(), job.jobType.toString()),
                    location = job.jobLocation ?: "",
                    salary = ""
                )
                _jobsUIState.value = _jobsUIState.value.copy(recommendedJobs = listOf(jobUiState), isLoading = false)
            } catch (throwable: Throwable) {
                errors.add(ErrorUiState(0, throwable.message.toString()))
                _jobsUIState.value = _jobsUIState.value.copy(
                    error = listOf(throwable.message.toString()),
                    isLoading = false
                )
            }
        }
    }
}