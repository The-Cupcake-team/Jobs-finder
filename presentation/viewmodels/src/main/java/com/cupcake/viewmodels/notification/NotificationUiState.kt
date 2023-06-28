package com.cupcake.viewmodels.notification

import com.cupcake.viewmodels.base.BaseErrorUiState

data class NotificationUiState(
    val isLoading: Boolean = false,
    val errorMessage: BaseErrorUiState? = null,
    val notificationList: List<NotificationDataUiState> = emptyList(),
    val isSuccess: Boolean = true,
) {
    data class NotificationDataUiState(
        val name: String = "",
        val avatar: String = "",
        val type: String = "",
        val message: String = "",
        val created: String = "",
        val id: String = "",
    )

}
