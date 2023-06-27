package com.cupcake.ui.create_job

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.ItemCreateJobFormTwoBinding
import com.cupcake.ui.utill.attachTo
import com.cupcake.viewmodels.create_job.CreateJobEvent
import com.cupcake.viewmodels.create_job.CreateJobViewModel
import kotlinx.coroutines.launch

class CreateJobFormTwoFragment : BaseFragment<ItemCreateJobFormTwoBinding, CreateJobViewModel>(
    R.layout.item_create_job_form_two, CreateJobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onChangeValueSlider()
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
            CreateJobEvent.JobCreated -> {
                Toast.makeText(requireContext(), "Job Created", Toast.LENGTH_SHORT).show()
            }
            is CreateJobEvent.ShowError -> {
                Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    private fun onChangeValueSlider() {
        binding.rangeSliderSalary.addOnChangeListener { slider, _, _ ->
            viewModel.onChangeRangSalary(slider.values)

        }

    }

}