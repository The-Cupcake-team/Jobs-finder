package com.cupcake.viewmodels.profile.jobs

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.models.Job
import com.cupcake.usecase.profile_job.GetRecentJobsUseCase
import com.cupcake.usecase.profile_job.GetSavedJobsUseCas
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ProfileJobsViewModel @Inject constructor(
    private val getRecentJobsUseCase: GetRecentJobsUseCase,
    private val getSavedJobsUseCase: GetSavedJobsUseCas
) : BaseViewModel<ProfileJobsUIState>(ProfileJobsUIState()),
    ProfileJobListener {

    private val _event = MutableSharedFlow<ProfileJobsEvent>()
    val event = _event.asSharedFlow()
    init {
        getRecentJobs()
        getSavedJobs()

    }
    private fun getRecentJobs() {
        tryToExecute(
            { getRecentJobsUseCase().map { it.toProfileJobUiState() } },
            ::onRecentJobsSuccess,
            ::onError
        )
    }


        private fun onRecentJobsSuccess(recentJobs: List<ProfileJobUiState>) {
        _state.update { it.copy(RecentJobs = recentJobs, isLoading = false) }
            Log.v("Abdu", _state.value.RecentJobs.toString())

        }

    private fun getSavedJobs() {
        tryToExecute(
            { getSavedJobsUseCase().map { it.toProfileJobUiState() } },
            ::onSavedJobsSuccess,
            ::onError
        )
    }

    private fun onSavedJobsSuccess(savedJobs: List<ProfileJobUiState>) {
        _state.update { it.copy(SavedJobs = savedJobs, isLoading = false) }
        Log.v("Abdu", _state.value.SavedJobs.toString())
    }

    private fun onError(error: BaseErrorUiState) {
        _state.update { it.copy(error = error, isLoading = false) }
    }

    override fun onCardClickListener(id: String) {
        viewModelScope.launch { _event.emit(ProfileJobsEvent.JobCardClick(id)) }
    }
    override fun onImageViewMoreClickListener(model: ProfileJobUiState) {
        viewModelScope.launch { _event.emit(ProfileJobsEvent.OnMoreOptionClickListener(model)) }
    }

    fun onRetryClicked(){
        _state.update {it.copy(error = null, isLoading = true) }
        getRecentJobs()
        getSavedJobs()
    }

    private fun Job.toProfileJobUiState() = ProfileJobUiState(
        id = id,
        image = "https://coursera-course-photos.s3.amazonaws.com/e3/f27630d13511e88dd241e68ded0cea/K_logo_800x800.png?auto=format%2Ccompress&dpr=1",
        title = jobTitle.title ?: "",
        companyName = company,
        detailsChip = listOf(workType, jobType),
        location = jobLocation,
        salary = jobSalary.toString()
    )


}