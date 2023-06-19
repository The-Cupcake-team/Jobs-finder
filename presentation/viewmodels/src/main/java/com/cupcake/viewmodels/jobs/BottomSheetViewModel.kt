package com.cupcake.viewmodels.jobs

import androidx.lifecycle.viewModelScope
import com.cupcake.viewmodels.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BottomSheetViewModel
    : BaseViewModel<JobsUiState>(JobsUiState()), BottomSheetListener {
    private val _event = MutableSharedFlow<BottomSheetEvent>()
    val event = _event.asSharedFlow()
    override fun onShareClickListener(id: String) {
        viewModelScope.launch { _event.emit(BottomSheetEvent.OnShareClickListener(id)) }


    }

    override fun onSaveListener(model: JobUiState) {
        viewModelScope.launch { _event.emit(BottomSheetEvent.OnSaveListener(model)) }

    }


}