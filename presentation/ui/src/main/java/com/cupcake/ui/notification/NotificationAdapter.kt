package com.cupcake.ui.notification

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.notification.NotificationInteractionListener
import com.cupcake.viewmodels.notification.NotificationUiState


class NotificationAdapter(
    items: List<NotificationUiState.NotificationDataUiState>,
    listener: NotificationInteractionListener,
) :
    BaseAdapter<NotificationUiState.NotificationDataUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_notificaton

    override fun areItemsEqual(
        oldItem: NotificationUiState.NotificationDataUiState,
        newItem: NotificationUiState.NotificationDataUiState
    ): Boolean {
        return oldItem.id == newItem.id
    }
}


