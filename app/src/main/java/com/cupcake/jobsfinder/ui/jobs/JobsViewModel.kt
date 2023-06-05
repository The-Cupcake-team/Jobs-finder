package com.cupcake.jobsfinder.ui.jobs

import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.useCase.GetAllJobUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
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
    private val getAllJobUseCase: GetAllJobUseCase
) : BaseViewModel() {

    private val _jobsUIState = MutableStateFlow(JobsUiState())
    val jobsUIState = _jobsUIState.asStateFlow()

    init {
        viewModelScope.launch {
            getRecommendedJobs()
        }
    }

    private fun getRecommendedJobs() {
        _jobsUIState.update { it.copy(isLoading = true) }

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _jobsUIState.update { it.copy(isLoading = false, error = listOf(throwable.message.toString())) }
        }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val jobs = getAllJobUseCase(RECOMMENDED_JOB_LIMIT)
                .map { job ->
                    JobUiState(
                        image = "",
                        title = job.jobTitle.title,
                        companyName = job.company,
                        detailsChip = listOf(job.workType, job.jobType),
                        location = job.jobLocation,
                        salary = job.salary
                    )
                }
            _jobsUIState.update { it.copy(job = jobs, isLoading = false) }
        }

    }

    companion object {
        private const val RECOMMENDED_JOB_LIMIT = 10
    }
}