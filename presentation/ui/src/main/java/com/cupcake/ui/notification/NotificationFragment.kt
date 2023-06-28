package com.cupcake.ui.notification

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentNotificationBinding
import com.cupcake.viewmodels.notification.NotificationViewModel

class NotificationFragment : BaseFragment<FragmentNotificationBinding, NotificationViewModel>(
    R.layout.fragment_notification,
    NotificationViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNotificationsRecyclerView()
        NavigationUI.setupWithNavController(binding.toolBar, findNavController())
    }

    private fun setupNotificationsRecyclerView() {
        val adapter = NotificationAdapter(listOf(), viewModel)
        binding.RecyclerViewNotification.adapter = adapter
        binding.RecyclerViewNotification.itemAnimator = null
    }
}