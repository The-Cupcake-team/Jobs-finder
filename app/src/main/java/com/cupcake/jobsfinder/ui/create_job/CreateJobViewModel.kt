package com.cupcake.jobsfinder.ui.create_job

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.domain.usecase.CreateJobUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import com.cupcake.jobsfinder.ui.create_job.state.CreateJobUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateJobViewModel @Inject constructor(
    private val createJob: CreateJobUseCase
) : BaseViewModel<CreateJobUiState>(CreateJobUiState()) {

    private val _jobUiState = MutableStateFlow(CreateJobUiState())
    val jobUiState = _jobUiState.asStateFlow()

    private val _event = MutableLiveData<CreateJobEvent>()
    val event: LiveData<CreateJobEvent> = _event

    fun createJob() {
        viewModelScope.launch {
            try {
                _jobUiState.update { it.copy(isLoading = true) }
                val jobState = createJob(
                    CreateJobUseCase.ParamJobInfo(
                        jobTitleId = _jobUiState.value.jobFormUiState.idJobTitle,
                        company = _jobUiState.value.jobFormUiState.company,
                        workType = _jobUiState.value.jobFormUiState.workType,
                        jobType = _jobUiState.value.jobFormUiState.jobType,
                        jobLocation = _jobUiState.value.jobFormUiState.jobLocation,
                        jobDescription = _jobUiState.value.jobFormUiState.jobDescription,
                        jobSalary = _jobUiState.value.jobFormUiState.salary,
                    )
                )

                if (jobState) {
                    onSuccessCreateJob()
                }

            } catch (e: Exception) {
                onCreateJobError(e.message.toString())
            }
        }
    }

    private fun onSuccessCreateJob() {
        _jobUiState.update { it.copy(isLoading = false) }
        // more logic
    }

    private fun onCreateJobError(errorMessage: String) {
        _jobUiState.update {
            it.copy(
                isLoading = false,
                error = errorMessage,
            )
        }
    }

    fun handleEvent(event: CreateJobEvent) {
        when (event) {
            is CreateJobEvent.PageScrolled -> {
                onChangeIndexViewPager(event.index)
            }

        }
    }

    private fun onChangeIndexViewPager(index: Int) {
        _jobUiState.update {
            it.copy(
                activeIconToolBar = getIconToolBar(index),
                formNumber = getFormNumber(index),
                titleToolBar = getTitleToolBar(index)
            )
        }
    }

    private fun getFormNumber(index: Int): Int {
        return if (index == 0) R.string.page_number_one else R.string.page_number_two
    }

    private fun getIconToolBar(index: Int): Int {
        return if (index == 0) R.drawable.ic_close else R.drawable.back
    }

    private fun getTitleToolBar(index: Int): Int {
        return if (index == 0) R.string.next else R.string.post
    }


}