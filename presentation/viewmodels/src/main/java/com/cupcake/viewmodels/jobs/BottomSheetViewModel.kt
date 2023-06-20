package com.cupcake.viewmodels.jobs

import android.util.Log
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
    : BaseViewModel<BottomSheetUiState>(BottomSheetUiState()), BottomSheetListener {
    private val _event = MutableSharedFlow<BottomSheetEvent>()
    val event = _event.asSharedFlow()
    lateinit var jobUiState: JobUiState
    override fun onShareClickListener() {
        viewModelScope.launch { _event.emit(BottomSheetEvent.OnShareClickListener(jobUiState.id)) } }

      fun saveJob() {
          viewModelScope.launch(Dispatchers.IO) {
            saveJobUseCase(jobUiState.toJobWithTitle())
          }
          viewModelScope.launch { _event.emit(BottomSheetEvent.OnSaveListener) }
}
    fun changeSavedState(){
        viewModelScope.launch(Dispatchers.IO){
            saveJobUseCase.isAlreadyExist(jobUiState.id).apply {
                _state.update {
                    it.copy(
                        isSaved = this,
                        textSaved = if (this) "Saved" else "Save"
                    )
                }
            }

    }
    }


    private fun onJobSaveSuccess(isSaved: Boolean){
        //todo: change ui state
    }
    private fun onError(error: BaseErrorUiState) {
//        _state.update { it.copy(error = error, isLoading = false) }
    }
}