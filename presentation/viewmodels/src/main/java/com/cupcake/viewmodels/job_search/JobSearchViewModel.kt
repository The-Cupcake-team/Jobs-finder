package com.cupcake.viewmodels.job_search

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.JobSearchFilterUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class JobSearchViewModel @Inject constructor(
    private val jobSearchFilterUseCase: JobSearchFilterUseCase
) : BaseViewModel<JopSearchUIState>(JopSearchUIState()) {

    private val _event = MutableSharedFlow<SearchJobEvent>()
    val event = _event.asSharedFlow()


    private fun getJobs() {
        tryToExecute(
            {jobSearchFilterUseCase().map { it.toJobItemUiState() }},
            ::onSearchJobSuccess,
            ::onError
        )
    }

    private fun onSearchJobSuccess(jobs: List<JobItemUiState>) {
        _state.update { it.copy(searchResult = jobs, isLoading = false) }
    }

    private fun onError(error: BaseErrorUiState) {
        _state.update { it.copy(error = error, isLoading = false) }
    }

     suspend fun onSearchInputChange(newSearchInput: String){
        //todo handel search change input
        _state.update { it.copy(searchInput = newSearchInput) }
    }

    private suspend fun onSelectJobFilter(jobFilter: JobFilterUIState){
        //todo handel search by job Filter types
    }

    private suspend fun onSearch(){
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val searchResult = jobSearchFilterUseCase(
                location = _state.value.jobFilterUIState.location,
                jobType = _state.value.jobFilterUIState.jobType,
                workType = _state.value.jobFilterUIState.workType,
                experienceLevel = _state.value.jobFilterUIState.experience,
                salaryRange = Pair(_state.value.jobFilterUIState.salary.minSalary,
                    _state.value.jobFilterUIState.salary.maxSalary)
                )

            _state.update { it.copy(isLoading = false, searchResult = searchResult.map { job ->
                job.toJobItemUiState()
            }) }
        }
    }
}