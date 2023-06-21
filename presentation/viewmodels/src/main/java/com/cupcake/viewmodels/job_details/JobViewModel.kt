package com.cupcake.viewmodels.job_details

import com.cupcake.models.Job
import com.cupcake.usecase.GetJobByIdUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val getJobByIdUseCase: GetJobByIdUseCase
) : BaseViewModel<JobDetailsUiState>(JobDetailsUiState()) {

    var jobId : String = ""
        fun getJobDetails(jobId: String) {
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
    fun onRetryClicked(){
        _state.update {it.copy(error = null, isLoading = true) }
        getJobDetails(jobId)
    }
}