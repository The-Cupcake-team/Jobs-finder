package com.cupcake.viewmodels.job_details

import com.cupcake.models.Job
import com.cupcake.usecase.GetJobByIdUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val getJobByIdUseCase: GetJobByIdUseCase
) : BaseViewModel<JobDetailUiState>(JobDetailUiState()) {

    init {
        getJobDetails("438cd8f7-af62-4796-84be-a98807e874d8")
    }

    private fun getJobDetails(jobId: String) {
        tryToExecute(
            { getJobByIdUseCase(jobId) },
            ::onGetJobDetailsSuccess,
            ::onGetJobDetailsFailure
        )
    }

    private fun onGetJobDetailsSuccess(jobDetails: Job) {
        updateState {
            it.copy(
                isLoading = false,
                error = null,
                job = jobDetails.toJobsDetailsUiState()
            ) }
    }

    private fun onGetJobDetailsFailure(error: BaseErrorUiState) {
        updateState { it.copy(isLoading = false, error = error) }
    }

    private fun Job.toJobsDetailsUiState(): JobsDetailsUiState {
        return JobsDetailsUiState(
            image = "",
            title = "Android Kotlin Developer",
            companyName = company,
            workType = workType,
            jobType = jobType,
            location = jobLocation,
            salary = jobSalary.toInt(),
            createdAt = createdAt,
            jobDescription = jobDescription
        )
    }
}