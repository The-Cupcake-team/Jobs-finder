package com.cupcake.viewmodels.job_details


import android.util.Log
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
        JobDetailUiState(job = JobsDetailsUiState())
    )
    val jobsUIState: StateFlow<JobDetailUiState> = _jobsUIState
    private val errors: MutableList<ErrorUiState> = mutableListOf()
    private val handler = CoroutineExceptionHandler { _, exception ->
        onGetJobDetailsFailure(exception)
    }
    private fun onGetJobDetailsFailure(exception: Throwable) {
        this._jobsUIState.update {
            it.copy(
                isLoading = false,
                error = listOf(exception.message.toString())
            )
        }
    }

    private fun GetJobDetails(){
        viewModelScope.launch(Dispatchers.IO + handler){
            Log.i("TAG", "EXECUTE")
            val jobDetails = getJobByIdUseCase("438cd8f7-af62-4796-84be-a98807e874d8")
            _jobsUIState.value = _jobsUIState.value.copy(
                isLoading = false,
                job = jobDetails.toJobsDetailsUiState()
            )
            Log.i("TAG", jobDetails.jobLocation)
        }
    }

    private fun Job.toJobsDetailsUiState() : JobsDetailsUiState {
        return JobsDetailsUiState(
            image = "",
            title = "Android Kotlin Developer",
            companyName = company,
            workType = workType ,
            jobType = jobType ,
            location = jobLocation ,
            salary = jobSalary.toString(),
            createdAt = createdAt ?: 1234,
            jobDescription = jobDescription?:""
        )
    }
    init {
        GetJobDetails()
    }
}