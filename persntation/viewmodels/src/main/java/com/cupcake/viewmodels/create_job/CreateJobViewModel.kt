package com.cupcake.viewmodels.create_job

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.usecase.CreateJobUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CreateJobViewModel @Inject constructor(
    private val createJob: CreateJobUseCase
) : BaseViewModel<CreateJobUiState>(CreateJobUiState()) {

    private val _event = MutableLiveData<CreateJobEvent>()
    val event: LiveData<CreateJobEvent> = _event

    fun createJob() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            {
                createJob(
                    CreateJobUseCase.ParamJobInfo(
                        jobTitleId = _state.value.jobFormUiState.idJobTitle,
                        company = _state.value.jobFormUiState.company,
                        workType = _state.value.jobFormUiState.workType,
                        jobType = _state.value.jobFormUiState.jobType,
                        jobLocation = _state.value.jobFormUiState.jobLocation,
                        jobDescription = _state.value.jobFormUiState.jobDescription,
                        jobSalary = _state.value.jobFormUiState.salary,
                    )
                )
            },
            ::onCreateJobSuccess,
            ::onCreateJobError
        )
    }

    private fun onCreateJobSuccess(result : Boolean) {
        _state.update { it.copy(isLoading = false) }
        // more logic
    }

    private fun onCreateJobError(error: Exception) {
        _state.update {
            it.copy(
                isLoading = false,
                error = error.message.toString(),
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
        _state.update {
            it.copy(
//                activeIconToolBar = getIconToolBar(index),
//                formNumber = getFormNumber(index),
//                titleToolBar = getTitleToolBar(index)
            )
        }
    }

    private fun getFormNumber(index: Int): Int {
//        return if (index == 0) R.string.page_number_one else R.string.page_number_two
        return 0
    }

    private fun getIconToolBar(index: Int): Int {
//        return if (index == 0) R.drawable.ic_close else R.drawable.back
        return 0
    }

    private fun getTitleToolBar(index: Int): Int {
//        return if (index == 0) R.string.next else R.string.post
        return 0
    }


}