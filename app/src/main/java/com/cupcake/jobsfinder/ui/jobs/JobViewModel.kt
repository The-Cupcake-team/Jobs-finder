package com.cupcake.jobsfinder.ui.jobs


import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.useCase.GetJobByIdUseCase
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

    private val _jobsUIState = MutableStateFlow(JobDetailUiState(job =JobsDetailsUiState() ))
    val jobsUIState: StateFlow<JobDetailUiState> = _jobsUIState
    private val errors: MutableList<ErrorUiState> = mutableListOf()

    init {
        getJobById("438cd8f7-af62-4796-84be-a98807e874d8")
    }

    private fun getJobById(jobId: String) {
        _jobsUIState.value = _jobsUIState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val job = getJobByIdUseCase(jobId)
                val jobUiState = JobsDetailsUiState(
                    image = "",
                    title = job.jobTitleId.toString(),
                    companyName = job.company ?: "",
                    workType = job.workType.toString(),
                    jobType=job.jobType.toString(),
                    location = job.jobLocation ?: "",
                    salary = job.jobSalary.toString() ?:"",
                    createdAt = job.createdAt ?: 0,
                    jobDescription=job.jobDescription.toString(),
                )
                _jobsUIState.value = _jobsUIState.value.copy(job = jobUiState, isLoading = false)
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