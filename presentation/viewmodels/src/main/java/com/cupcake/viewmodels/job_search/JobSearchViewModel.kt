package com.cupcake.viewmodels.job_search

import android.app.job.JobWorkItem
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.JobSearchFilterUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.create_job.WorkType
import com.cupcake.viewmodels.jobs.JobsViewModel_HiltModules
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class JobSearchViewModel @Inject constructor(
    private val jobSearchFilterUseCase: JobSearchFilterUseCase
) : BaseViewModel<JopSearchUIState>(JopSearchUIState()), JobsSearchInteractionListener {

    private val _event = MutableSharedFlow<SearchJobEvent>()
    val event = _event.asSharedFlow()

    private val _filterState: MutableStateFlow<JobFilterUIState> = MutableStateFlow(JobFilterUIState())

    private val _salaryState: MutableStateFlow<SalaryUIState> =  MutableStateFlow(SalaryUIState())

    private fun getJobs() {
        viewModelScope.launch {
            _state.debounce(1500).collect{itemState ->
                tryToExecute(
                    {jobSearchFilterUseCase(
                        searchTitle = itemState.searchInput,
                        location = itemState.jobFilterUIState.location,
                        jobType = itemState.jobFilterUIState.jobType,
                        workType = itemState.jobFilterUIState.workType,
                        experienceLevel = itemState.jobFilterUIState.experience,
                        salaryRange = Pair(itemState.jobFilterUIState.salary.minSalary.toDouble(),
                            itemState.jobFilterUIState.salary.maxSalary.toDouble())
                    ).map { it.toJobItemUiState() }},
                    ::onSearchJobSuccess,
                    ::onError
                )
            }
        }
    }

    private fun onSearchJobSuccess(jobs: List<JobItemUiState>) {
        _state.update { it.copy(searchResult = jobs, isLoading = false) }
        Log.v("hassanSearch", _state.value.toString())
    }

    private fun onError(error: BaseErrorUiState) {
        _state.update { it.copy(error = error, isLoading = false) }
    }

    fun onSearchInputChange(newSearchInput: CharSequence){
        //todo handel search change input
        _state.update { it.copy(searchInput = newSearchInput.toString()) }
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

    fun onMaxSalaryChange(maxSalary: CharSequence){
        _salaryState.update { it.copy(maxSalary = maxSalary.toString()) }
    }

    fun onMinSalaryChange(minSalary: CharSequence){
       _salaryState.update { it.copy(minSalary = minSalary.toString()) }
    }

    fun onFilterClicked(){
        viewModelScope.launch {  _event.emit(SearchJobEvent.OnFilterClicked) }
    }

    fun onApplyClicked(){
        _filterState.update { it.copy(salary = _salaryState.value) }
        _state.update { it.copy(jobFilterUIState = _filterState.value) }
        getJobs()
        viewModelScope.launch {
            _event.emit(SearchJobEvent.OnApplyButtonClicked)
        }
    }

    override fun onCardClickListener(id: String) {
        viewModelScope.launch {
            _event.emit(SearchJobEvent.JobCardClick(id))
        }
    }

    override fun onImageViewMoreClickListener(model: JopSearchUIState) {
        viewModelScope.launch {
            _event.emit(SearchJobEvent.OnMoreOptionClickListener(model))
        }
    }

}