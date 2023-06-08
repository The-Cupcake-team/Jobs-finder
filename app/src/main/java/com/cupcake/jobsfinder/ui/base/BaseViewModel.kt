package com.cupcake.jobsfinder.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


abstract class BaseViewModel<STATE>(initialUiState: STATE) : ViewModel() {

    protected val _state: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialUiState) }
    val state: StateFlow<STATE> by lazy { _state.asStateFlow() }

}