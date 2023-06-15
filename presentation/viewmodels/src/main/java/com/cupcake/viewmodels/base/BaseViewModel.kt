package com.cupcake.viewmodels.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cupcake.models.ErrorType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel<STATE>(initialUiState: STATE) : ViewModel() {

    protected val _state: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialUiState) }
    val state: StateFlow<STATE> by lazy { _state.asStateFlow() }

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
                onError(BaseErrorUiState.Disconnected)
            } catch (error: ErrorType.Validation) {
                onError(BaseErrorUiState.UnAuthorized)
            } catch (error: ErrorType.Server) {
                onError(BaseErrorUiState.ServerError)
            } catch (error: ErrorType.Unknown) {
                onError(BaseErrorUiState.NoFoundError)
            } catch (error: Throwable) {
                onError(BaseErrorUiState.NoFoundError)
            }
        }
    }

}