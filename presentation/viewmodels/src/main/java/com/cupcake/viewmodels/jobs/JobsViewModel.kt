package com.cupcake.viewmodels.jobs

import com.cupcake.usecase.job.GetJobsInUserLocationUseCase
import com.cupcake.usecase.job.GetRecommendedJobsUseCase
import com.cupcake.usecase.job.GetTopSalaryInUserLocationUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
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
        tryToExecute(
            suspend { getRecommendedJobs(10).map { it.toJobUiState() } },
            ::onRecommendedJobsSuccess,
            ::onError
        )
    }

    private fun onRecommendedJobsSuccess(recommendedJobs: List<JobUiState>) {
        _state.update { it.copy(recommendedJobs = recommendedJobs, isLoading = false) }
    }

    private fun getTopSalaryJobs() {
        tryToExecute(
            suspend { getTopSalaryInUserLocation(10).map { it.toJobUiState() } },
            ::onTopSalaryJobsSuccess,
            ::onError
        )
    }

    private fun onTopSalaryJobsSuccess(topSalaryJobs: List<JobUiState>) {
        _state.update { it.copy(topSalaryJobs = topSalaryJobs, isLoading = false) }
    }

    private fun getInLocationJobs() {
        tryToExecute(
            suspend { getJobsInUserLocation(10).map { it.toJobUiState() } },
            ::onInLocationJobsJobsSuccess,
            ::onError
        )
    }

    private fun onInLocationJobsJobsSuccess(inLocationJobs: List<JobUiState>) {
        _state.update { it.copy(onLocationJobs = inLocationJobs, isLoading = false) }
    }

    private fun onError(error: Exception) {
        _state.update { it.copy(error = listOf(error.message!!), isLoading = false) }
    }

    companion object {
        private const val RECOMMENDED_JOB_LIMIT = 10
    }
}
