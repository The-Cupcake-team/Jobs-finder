package com.cupcake.jobsfinder.ui.create_job

import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.data.remote.ResultState
import com.cupcake.jobsfinder.data.remote.model.JobDto
import com.cupcake.jobsfinder.domain.CreateJobUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateJobViewModel @Inject constructor(
    private val createJobUseCase: CreateJobUseCase
) : BaseViewModel() {

    private val _jobStateFlow =
        MutableStateFlow<ResultState<JobDto>>(ResultState.Error(Throwable("Job not created")))
    val jobStateFlow: StateFlow<ResultState<JobDto>> = _jobStateFlow

    private lateinit var createJobJob: Job

    fun createJobWithFlow(
        jobDto: JobDto
    ) {
        createJobJob = viewModelScope.launch {
            try {
                _jobStateFlow.value = ResultState.Loading
                val job = createJobUseCase.createJob(jobDto)
                _jobStateFlow.value = job
            } catch (e: Exception) {
                _jobStateFlow.value = ResultState.Error(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        createJobJob.cancel()
    }
}