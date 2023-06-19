package com.cupcake.viewmodels.jobs

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.SaveJobUseCase
import com.cupcake.usecase.job.GetJobsOnUserLocationUseCase
import com.cupcake.usecase.job.GetPopularJobsUseCase
import com.cupcake.usecase.job.GetRecommendedJobsUseCase
import com.cupcake.usecase.job.GetTopSalaryInUserLocationUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JobsViewModel @Inject constructor(
    private val getPopularJobs: GetPopularJobsUseCase,
    private val getRecommendedJobs: GetRecommendedJobsUseCase,
    private val getJobsInUserLocation: GetJobsOnUserLocationUseCase,
    private val getTopSalaryInUserLocation: GetTopSalaryInUserLocationUseCase,
) : BaseViewModel<JobsUiState>(JobsUiState()), JobsListener {

    private val _event = MutableSharedFlow<JobsEvent>()
    val event = _event.asSharedFlow()

    init {
        getPopularJobs()
        getRecommendedJobs()
        getTopSalaryJobs()
        getOnLocationJobs()
    }

    private fun getPopularJobs() {
        tryToExecute(
            { getPopularJobs(POPULAR_JOB_LIMIT).map { it.toJobTitleUiState() } },
            ::onPopularJobsSuccess,
            ::onError
        )
    }

    private fun onPopularJobsSuccess(popularJobs: List<JobTitleUiState>) {
        _state.update { it.copy(popularJobs = popularJobs, isLoading = false) }
    }

    private fun getRecommendedJobs() {
        tryToExecute(
            { getRecommendedJobs(RECOMMENDED_JOB_LIMIT).map { it.toJobUiState() } },
            ::onRecommendedJobsSuccess,
            ::onError
        )
    }

    private fun onRecommendedJobsSuccess(recommendedJobs: List<JobUiState>) {
        _state.update { it.copy(recommendedJobs = recommendedJobs, isLoading = false) }
    }

    private fun getTopSalaryJobs() {
        tryToExecute(
            { getTopSalaryInUserLocation(TOP_SALARY_JOB_LIMIT).map { it.toJobUiState() } },
            ::onTopSalaryJobsSuccess,
            ::onError
        )
    }

    private fun onTopSalaryJobsSuccess(topSalaryJobs: List<JobUiState>) {
        _state.update { it.copy(topSalaryJobs = topSalaryJobs, isLoading = false) }
    }

    private fun getOnLocationJobs() {
        tryToExecute(
            { getJobsInUserLocation(ON_LOCATION_JOB_LIMIT).map { it.toJobUiState() } },
            ::onLocationJobsSuccess,
            ::onError
        )
    }

    private fun onLocationJobsSuccess(onLocationJobs: List<JobUiState>) {
        _state.update { it.copy(onLocationJobs = onLocationJobs, isLoading = false) }
    }

    private fun onError(error: BaseErrorUiState) {
        _state.update { it.copy(error = error, isLoading = false) }
    }



    companion object {
        private const val POPULAR_JOB_LIMIT = 10
        private const val RECOMMENDED_JOB_LIMIT = 10
        private const val TOP_SALARY_JOB_LIMIT = 10
        private const val ON_LOCATION_JOB_LIMIT = 10
    }

    override fun onCardClickListener(id: String) {
        viewModelScope.launch { _event.emit(JobsEvent.JobCardClick(id)) }
    }

    override fun onChipClickListener(jobTitle: String) {
        viewModelScope.launch { _event.emit(JobsEvent.JobChipClick(jobTitle)) }
    }

    override fun onSearchBoxClickListener() {
        viewModelScope.launch { _event.emit(JobsEvent.SearchBoxClick) }
    }
    override fun onFloatingActionClickListener() {
        viewModelScope.launch { _event.emit(JobsEvent.OnFloatingActionClickListener) }
    }

    override fun onImageViewMoreClickListener(model:JobUiState) {
        viewModelScope.launch { _event.emit(JobsEvent.OnImageViewMoreClickListener(model)) }
    }


    fun onRetryClicked(){
        _state.update {it.copy(error = null, isLoading = true) }
        getPopularJobs()
        getRecommendedJobs()
        getTopSalaryJobs()
        getOnLocationJobs()
    }

}
