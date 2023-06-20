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
        getJobDetails("f2ac1ca6-c036-4b2c-9712-a5d6766c61e4")
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
}