package com.cupcake.ui.create_job

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.ItemCreateJobFormOneBinding
import com.cupcake.viewmodels.create_job.CreateJobEvent
import com.cupcake.viewmodels.create_job.CreateJobViewModel
import kotlinx.coroutines.launch

class CreateJobFormOneFragment : BaseFragment<ItemCreateJobFormOneBinding, CreateJobViewModel>(
    R.layout.item_create_job_form_one, CreateJobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePostEvents()
        NavigationUI.setupWithNavController(binding.toolBar, findNavController())
    }

    fun observePostEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    event.getContentIfNotHandled()?.let { event ->
                        handJobEvent(event)
                    }
                }
            }
        }
    }

    private fun handJobEvent(event: CreateJobEvent) {
        when (event) {
            CreateJobEvent.NextPage -> {
                findNavController().navigate(CreateJobFormOneFragmentDirections.actionCreateJobFormOneFragmentToCreateJobFormTwoFragment())
            }
            else -> {}
        }
    }

}