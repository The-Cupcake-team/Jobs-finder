package com.cupcake.ui.create_job

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.create_job.adapter.ViewPagerCreateJobAdapter
import com.cupcake.ui.databinding.FragmentCreateJobBinding
import com.cupcake.viewmodels.create_job.CreateJobEvent
import com.cupcake.viewmodels.create_job.CreateJobViewModel
import kotlinx.coroutines.launch

class CreateJobFragment : BaseFragment<FragmentCreateJobBinding, CreateJobViewModel>(
    R.layout.fragment_create_job, CreateJobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    private val fragmentTasks = mapOf(
        0 to CreateJobFormOneFragment(),
        1 to CreateJobFormTwoFragment(),
    )
    private var validationInProgress: Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = ViewPagerCreateJobAdapter(
            fragmentManager = requireActivity().supportFragmentManager,
            fragmentItems = fragmentTasks,
            lifecycle = lifecycle,
        )
        binding.apply {
            viewPagerCreateJob.adapter = adapter
            onChangePageSelected()
        }

        lifecycleScope.launch {
            viewModel.event.collect {
                handleCreateJobEvents(it)
            }
        }
//        viewLifecycleOwner.lifecycleScope.launch {
////            viewModel.state
////                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
////                .collect { uiState ->
////                    Log.v("ameerxyz","uiState ${uiState.progressStep}")
////                    if (uiState.progressStep != 1) {
////                        binding.viewPagerCreateJob.currentItem = 0
////                    }else{
////                        binding.viewPagerCreateJob.currentItem = 1
////                    }
////
////                }
//            viewModel.event.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect { event ->
//                Log.v("ameerxyz", "event ${event}")
//                event(event)
//            }
//        }

    }

    private fun handleCreateJobEvents(event: CreateJobEvent) {
        when (event) {
            is CreateJobEvent.PageScrolled -> {
                if (event.index != 1) {
                    binding.viewPagerCreateJob.currentItem = 0
                } else {
                    binding.viewPagerCreateJob.currentItem = 1
                }
            }

            is CreateJobEvent.HeaderButtonClicked -> {
                if (event.index != 1) {
                    binding.viewPagerCreateJob.currentItem = 0
                } else {
                    binding.viewPagerCreateJob.currentItem = 1
                    viewModel.createJob()
                    onClickBackNavigationIcon()
                }
            }
        }
    }

    private fun onChangePageSelected() {
        binding.apply {
            viewPagerCreateJob.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    viewModel?.handleEvent(CreateJobEvent.PageScrolled(position))
                }
            })
        }
    }

    private fun onClickBackNavigationIcon() {
        binding.toolBar.setNavigationOnClickListener { view ->
            view.findNavController().popBackStack()
        }
    }

}