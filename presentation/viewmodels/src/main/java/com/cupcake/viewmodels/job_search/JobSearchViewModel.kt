package com.cupcake.viewmodels.job_search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.JobSearchFilterUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class JobSearchViewModel @Inject constructor(
    private val jobSearchFilterUseCase: JobSearchFilterUseCase
) : BaseViewModel<JopSearchUIState>(JopSearchUIState()) {

    private val _event = MutableSharedFlow<SearchJobEvent>()
    val event = _event.asSharedFlow()


    init {
        viewModelScope.launch {
            state.debounce(1000).collect{
                Log.v("hassanSearch", it.toString())
            }
        }
    }
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

    fun onSearchInputChange(newSearchInput: CharSequence){
        //todo handel search change input
        _state.update { it.copy(searchInput = newSearchInput.toString()) }
    }

    private suspend fun onSelectJobFilter(jobFilter: JobFilterUIState){
        //todo handel search by job Filter types
    }

    fun onFilterClicked(){
        viewModelScope.launch {  _event.emit(SearchJobEvent.OnFilterClicked) }
    }

    fun onApplyClicked(){
        viewModelScope.launch {
            _event.emit(SearchJobEvent.OnApplyButtonClicked)
        }
    }

//    private suspend fun onSearch(){
//        _state.update { it.copy(isLoading = true) }
//        viewModelScope.launch {
//            val searchResult = jobSearchFilterUseCase(
//                location = _state.value.jobFilterUIState.location,
//                jobType = _state.value.jobFilterUIState.jobType,
//                workType = _state.value.jobFilterUIState.workType,
//                experienceLevel = _state.value.jobFilterUIState.experience,
//                salaryRange = Pair(_state.value.jobFilterUIState.salary.minSalary,
//                    _state.value.jobFilterUIState.salary.maxSalary)
//                )
//
//            _state.update { it.copy(isLoading = false, searchResult = searchResult.map { job ->
//                job.toJobItemUiState()
//            }) }
//        }
//    }
}