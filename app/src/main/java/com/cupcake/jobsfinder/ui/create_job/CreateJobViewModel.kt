package com.cupcake.jobsfinder.ui.create_job

import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.useCase.CreateJobUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import com.cupcake.jobsfinder.ui.create_job.state.CreateJobUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateJobViewModel @Inject constructor(
    private val createJob: CreateJobUseCase
) : BaseViewModel() {

    private val _jobUiState = MutableStateFlow(CreateJobUiState())
    val jobUiState = _jobUiState.asStateFlow()


    fun createJob() {
        viewModelScope.launch {
            try {
                _jobUiState.update { it.copy(isLoading = true) }
                val job = createJob(
                    CreateJobUseCase.ParamJobInfo(
                        idJobTitle = _jobUiState.value.jobFormUiState.idJobTitle,
                        company = _jobUiState.value.jobFormUiState.company,
                        workType = _jobUiState.value.jobFormUiState.workType,
                        jobType = "",
                        jobLocation = _jobUiState.value.jobFormUiState.jobLocation,
                        jobDescription = _jobUiState.value.jobFormUiState.jobDescription,
                        jobSalary = _jobUiState.value.jobFormUiState.price,
                    )
                )

                if (job) {
                    onSuccessCreateJob()
                }

            } catch (e: Exception) {
                onCreateJobError(e.message.toString())
            }
        }
    }

    private fun onSuccessCreateJob() {
        _jobUiState.update { it.copy(isLoading = true) }
        // more logic
    }

    private fun onCreateJobError(errorMessage: String) {
        _jobUiState.update {
            it.copy(
                isLoading = false,
                error = errorMessage,
            )
        }
    }

}