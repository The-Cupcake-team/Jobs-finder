package com.cupcake.viewmodels.job_details


import androidx.lifecycle.viewModelScope
import com.cupcake.models.Job
import com.cupcake.usecase.GetJobByIdUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.jobs.ErrorUiState
import com.cupcake.viewmodels.jobs.JobsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val getJobByIdUseCase: GetJobByIdUseCase
) : BaseViewModel<JobsUiState>(JobsUiState()) {

    private val _jobsUIState = MutableStateFlow(
        JobDetailUiState(
            job = JobsDetailsUiState()
        )
    )
    val jobsUIState: StateFlow<JobDetailUiState> = _jobsUIState
    private val errors: MutableList<ErrorUiState> = mutableListOf()

    private fun onGetJobDetailsFailure(exception: Throwable) {
        this._jobsUIState.update {
            it.copy(
                isLoading = false,
                error = listOf(exception.message.toString())
            )
        }
    }

    private fun onGetJobDetailsSuccess(jobsDetails: Job) {
        _jobsUIState.value.copy(
            isLoading = false,
            job = jobsDetails.toJobsDetailsUiState()
        )
    }

    private suspend fun onInternetDisconnected() {
        _state.update { it.copy(isLoading = true) }
    }


    private fun Job.toJobsDetailsUiState(): JobsDetailsUiState {
        return JobsDetailsUiState(
            image = "",
            title = "title",
            companyName = this.company,
            workType = this.workType,
            jobType = this.jobType,
            location = this.jobLocation,
            salary = this.jobSalary.toString(),
            createdAt = this.createdAt,
            jobDescription = this.jobDescription
        )
    }


    init {
//        getJobById("438cd8f7-af62-4796-84be-a98807e874d8")
    }

//    private fun getJobById(jobId: String) {
//        _jobsUIState.value = _jobsUIState.value.copy(isLoading = true)
//
//        viewModelScope.launch {
//            try {
//                val job = getJobByIdUseCase(jobId)
//                val jobUiState = JobsDetailsUiState(
//                    image = "",
//                    title = job.jobTitleId.toString(),
//                    companyName = job.company ?: "",
//                    workType = job.workType.toString(),
//                    jobType=job.jobType.toString(),
//                    location = job.jobLocation ?: "",
//                    salary = job.jobSalary.toString() ?:"",
//                    createdAt = job.createdAt ?: 0,
//                    jobDescription=job.jobDescription.toString(),
//                )
//
//                _jobsUIState.value = _jobsUIState.value.copy(job = jobUiState, isLoading = false)
//
//            } catch (throwable: Throwable) {
//                errors.add(ErrorUiState(0, throwable.message.toString()))
//                _jobsUIState.value = _jobsUIState.value.copy(
//                    error = listOf(throwable.message.toString()),
//                    isLoading = false
//                )
//            }
//        }
//    }
}