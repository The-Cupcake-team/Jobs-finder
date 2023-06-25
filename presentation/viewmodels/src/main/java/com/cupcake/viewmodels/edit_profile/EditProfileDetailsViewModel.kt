package com.cupcake.viewmodels.edit_profile

import androidx.lifecycle.viewModelScope
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.login.LoginEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class EditProfileDetailsViewModel : BaseViewModel<EditProfileDetailsUiState>(
    EditProfileDetailsUiState()
) {

    private val _event = MutableSharedFlow<EditProfileDetailsEvent>()
    val event = _event.asSharedFlow()
    fun onEditLocationClick() {
        viewModelScope.launch { _event.emit(EditProfileDetailsEvent.EditLocationClick) }
    }
}