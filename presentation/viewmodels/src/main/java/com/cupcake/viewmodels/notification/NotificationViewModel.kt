package com.cupcake.viewmodels.notification

import com.cupcake.models.Notifications
import com.cupcake.usecase.GetNotificationsUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.notification.NotificationUiState.NotificationDataUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotificationsFakeData: GetNotificationsUseCase

) : BaseViewModel<NotificationUiState>(NotificationUiState()), NotificationInteractionListener {


    init {
        getNotificationsData()
    }

    private fun getNotificationsData() {
        tryToExecute(
            { getNotificationsFakeData() },
            ::onGetNotificationsSuccess,
            ::onGetNotificationsFailure,
        )
    }

    private fun onGetNotificationsSuccess(notifications: List<Notifications>) {
        _state.update {
            it.copy(
                isLoading = false,
                errorMessage = null,
                notificationList = notifications.map { notifications -> notifications.toUiNotifications() }
            )
        }
    }

    private fun onGetNotificationsFailure(error: BaseErrorUiState) {

        _state.update {
            it.copy(isLoading = false, errorMessage = error)
        }
    }

    private fun Notifications.toUiNotifications(): NotificationDataUiState {
        return NotificationDataUiState(
            id = id,
            name = name,
            type = type,
            message = message,
            created = created,
            avatar = avatar
        )
    }


}