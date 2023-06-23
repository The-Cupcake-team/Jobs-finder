package com.cupcake.viewmodels.notification

import com.cupcake.viewmodels.base.BaseErrorUiState

data class NotificationUiState(
    val isLoading: Boolean = false,
    val errorMessage: BaseErrorUiState? = null,
    val notificationList: List<String> = emptyList()
)
