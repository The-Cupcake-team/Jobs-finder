package com.cupcake.viewmodels.jobs

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.SaveJobUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    private val saveJobUseCase: SaveJobUseCase
)
    : BaseViewModel<JobsUiState>(JobsUiState()), BottomSheetListener {
    private val _event = MutableSharedFlow<BottomSheetEvent>()
    val event = _event.asSharedFlow()
    override fun onShareClickListener(id: String) {
        viewModelScope.launch { _event.emit(BottomSheetEvent.OnShareClickListener(id)) } }
    override fun onSaveListener(model: JobUiState) {
        viewModelScope.launch { _event.emit(BottomSheetEvent.OnSaveListener(model)) } }


      fun saveJob(jobUiState: JobUiState) {
        viewModelScope.launch(Dispatchers.IO) {
            saveJobUseCase(jobUiState.toJobWithTitle())
        }
    }

    private fun onJobSaveSuccess(isSaved: Boolean){
        //todo: change ui state
    }
    private fun onError(error: BaseErrorUiState) {
//        _state.update { it.copy(error = error, isLoading = false) }
    }
}