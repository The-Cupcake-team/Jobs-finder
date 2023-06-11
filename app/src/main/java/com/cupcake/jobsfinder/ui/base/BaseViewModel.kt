package com.cupcake.jobsfinder.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception


abstract class BaseViewModel<STATE>(initialUiState: STATE) : ViewModel() {

    protected val _state: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialUiState) }
    val state: StateFlow<STATE> by lazy { _state.asStateFlow() }

    fun <T> tryToExecute(
        callee: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = callee()
                onSuccess(result)
            } catch (error: Exception) {
                onError(error)
            }
        }
    }
}