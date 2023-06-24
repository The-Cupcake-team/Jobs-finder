package com.cupcake.viewmodels.job_search

import android.util.Log
import androidx.lifecycle.SavedStateHandle
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
    private val jobSearchFilterUseCase: JobSearchFilterUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<JopSearchUIState>(JopSearchUIState()), JobsSearchInteractionListener {

    private val _event = MutableSharedFlow<SearchJobEvent>()
    val event = _event.asSharedFlow()

    private val _filterState: MutableStateFlow<JobFilterUIState> = MutableStateFlow(JobFilterUIState())

    private val _salaryState: MutableStateFlow<SalaryUIState> =  MutableStateFlow(SalaryUIState())
    val salaryState: StateFlow<SalaryUIState> = _salaryState

    init {
        //getJobs()
    }
    private fun getJobs() {
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            _state.debounce(1000).collect {
                tryToExecute(
                    {jobSearchFilterUseCase(
                        searchTitle = it.searchInput,
                        location = it.jobFilterUIState.location,
                        jobType = it.jobFilterUIState.jobType,
                        workType = it.jobFilterUIState.workType,
                        experienceLevel = it.jobFilterUIState.experience,
                        salaryRange = Pair(it.jobFilterUIState.salary.minSalary,
                            it.jobFilterUIState.salary.maxSalary)
                    ).map { it.toJobItemUiState() }},
                    ::onSearchJobSuccess,
                    ::onError
                )
            }
        }
    }

    private fun onSearchJobSuccess(jobs: List<JobItemUiState>) {
        _state.update { it.copy(searchResult = jobs, isLoading = false, error = null) }
    }

    private fun onError(error: BaseErrorUiState) {
        _state.update { it.copy(error = error, isLoading = false) }
    }

    fun onSearchInputChange(newSearchInput: CharSequence){
        _state.update { it.copy(searchInput = newSearchInput.toString()) }
        getJobs()
    }

    fun initialSearchInput(jobTitle: String){
        _state.update { it.copy(searchInput = jobTitle) }
    }

    fun onLocationChange(location: CharSequence){
        _filterState.update { it.copy(location = location.toString()) }
    }

    fun onJobTypeChange(jobType: String){
        _filterState.update { it.copy(jobType = jobType) }
    }

    fun onWorkTypeChange(workType: String){
        _filterState.update { it.copy(workType = workType) }
    }

    fun onExperienceLevelChange(level: String){
        _filterState.update { it.copy(experience = level) }
    }

    fun onSalaryChange(minSalary: Double, maxSalary: Double){
        _salaryState.update { it.copy(minSalary = minSalary, maxSalary = maxSalary) }
    }

    fun onFilterClicked(){
        viewModelScope.launch {  _event.emit(SearchJobEvent.OnFilterClicked) }
    }

    fun onApplyClicked(){
        _filterState.update { it.copy(salary = _salaryState.value) }
        _state.update { it.copy(jobFilterUIState = _filterState.value) }
        viewModelScope.launch {
            _event.emit(SearchJobEvent.OnApplyButtonClicked)
        }
    }

    fun onClearClicked(){
        viewModelScope.launch { _event.emit(SearchJobEvent.OnClearButtonClicked) }
    }

    override fun onCardClickListener(id: String) {
        viewModelScope.launch {
            _event.emit(SearchJobEvent.JobCardClick(id))
        }
    }

    fun onRetryClicked(){
        onApplyClicked()
    }

}