package com.cupcake.viewmodels.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cupcake.models.ErrorType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


abstract class BaseViewModel<STATE>(initialUiState: STATE) : ViewModel() {

    protected val _state: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialUiState) }
    val state: StateFlow<STATE> by lazy { _state.asStateFlow() }
    protected fun updateState(block: (STATE) -> STATE) {
        _state.update { block(it) }
    }

    fun <T> tryToExecute(
        callee: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (BaseErrorUiState) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = callee()
                onSuccess(result)
            } catch (error: ErrorType.Network) {
                onError(BaseErrorUiState.Disconnected(error.message.toString()))
            } catch (error: ErrorType) {
                onError(BaseErrorUiState.UnAuthorized(error.message.toString()))
            } catch (error: ErrorType.Server) {
                onError(BaseErrorUiState.ServerError(error.message.toString()))
            } catch (error: ErrorType.Unknown) {
                onError(BaseErrorUiState.NoFoundError(error.message.toString()))
            } catch (error: Throwable) {
                onError(BaseErrorUiState.NoFoundError(error.message.toString()))
            }
        }
    }

}