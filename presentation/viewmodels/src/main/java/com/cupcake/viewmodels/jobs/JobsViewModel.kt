package com.cupcake.viewmodels.jobs

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.job.GetJobsInUserLocationUseCase
import com.cupcake.usecase.job.GetRecommendedJobsUseCase
import com.cupcake.usecase.job.GetTopSalaryInUserLocationUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JobsViewModel @Inject constructor(
    private val getRecommendedJobs: GetRecommendedJobsUseCase,
    private val getJobsInUserLocation: GetJobsInUserLocationUseCase,
    private val getTopSalaryInUserLocation: GetTopSalaryInUserLocationUseCase
) : BaseViewModel<JobsUiState>(JobsUiState()) {


    init {
        getRecommendedJobs()
        getTopSalaryJobs()
        getInLocationJobs()
    }

    private fun getRecommendedJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            val jobs = getRecommendedJobs(RECOMMENDED_JOB_LIMIT).map { it.toJobUiState() }
            _state.update { it.copy(recommendedJobs = jobs, isLoading = false) }
        }
    }

    private fun getTopSalaryJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            val jobs = getTopSalaryInUserLocation(RECOMMENDED_JOB_LIMIT).map { it.toJobUiState() }
            _state.update { it.copy(topSalaryJobs = jobs, isLoading = false) }
        }
    }

    private fun getInLocationJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            val jobs = getJobsInUserLocation(RECOMMENDED_JOB_LIMIT).map { it.toJobUiState() }
            _state.update { it.copy(onLocationJobs = jobs, isLoading = false) }
        }
    }

    companion object {
        private const val RECOMMENDED_JOB_LIMIT = 10
    }
}
