package com.cupcake.jobsfinder.ui.jobs

import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.useCase.job.GetJobsInUserLocationUseCase
import com.cupcake.jobsfinder.domain.useCase.job.GetRecommendedJobsUseCase
import com.cupcake.jobsfinder.domain.useCase.job.GetTopSalaryInUserLocationUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import com.cupcake.jobsfinder.ui.jobs.adapter.JobsListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val getRecommendedJobsUseCase: GetRecommendedJobsUseCase,
    private val getJobsInUserLocationUseCase: GetJobsInUserLocationUseCase,
    private val getTopSalaryInUserLocationUseCase: GetTopSalaryInUserLocationUseCase
) : BaseViewModel(), JobsListener {

    private val _jobsUIState = MutableStateFlow(JobsUiState())
    val jobsUIState = _jobsUIState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _jobsUIState.update { it.copy(isLoading = false, error = listOf(throwable.message.toString())) }
    }
    init {
        viewModelScope.launch {
            getRecommendedJobs()
            getTopSalaryJobs()
            getInLocationJobs()
        }
    }

    private fun getRecommendedJobs() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val jobs = getRecommendedJobsUseCase(RECOMMENDED_JOB_LIMIT).map { it.toJobUiState() }
            _jobsUIState.update { it.copy(recommendedJobs = jobs, isLoading = false) }
        }
    }

    private fun getTopSalaryJobs() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val jobs = getTopSalaryInUserLocationUseCase(RECOMMENDED_JOB_LIMIT).map { it.toJobUiState() }
            _jobsUIState.update { it.copy(topSalaryJobs = jobs, isLoading = false) }
        }
    }

    private fun getInLocationJobs() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val jobs = getJobsInUserLocationUseCase(RECOMMENDED_JOB_LIMIT)
                .map { it.toJobUiState() }
            _jobsUIState.update { it.copy(inLocationJobs = jobs, isLoading = false) }
        }
    }

    override fun onItemClickListener() {

    }

    override fun onRecommendedJobClickListener() {

    }

    override fun onTopSalaryJobClickListener() {

    }

    companion object {
        private const val RECOMMENDED_JOB_LIMIT = 10
    }
}
