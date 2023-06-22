package com.cupcake.viewmodels.notification

import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor() :
    BaseViewModel<NotificationUiState>(NotificationUiState()) {
}