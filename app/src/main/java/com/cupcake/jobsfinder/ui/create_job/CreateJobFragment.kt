package com.cupcake.jobsfinder.ui.create_job

import android.os.Bundle
import android.view.View
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentCreateJobBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment
import com.cupcake.jobsfinder.ui.create_job.adapter.ViewPagerCreateJobAdapter
import com.google.android.material.tabs.TabLayoutMediator

class CreateJobFragment : BaseFragment<FragmentCreateJobBinding, CreateJobViewModel>(
    R.layout.fragment_create_job, CreateJobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    private val fragmentTasks = mapOf(
        0 to CreateJobFormOneFragment(),
        1 to CreateJobFormTwoFragment(),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val titleTabTasks = resources.getStringArray(R.array.task)

        val adapter = ViewPagerCreateJobAdapter(
            fragmentManager = requireActivity().supportFragmentManager,
            fragmentItems = fragmentTasks,
            lifecycle = lifecycle,
        )
        binding?.apply {

            viewPagerCreateJob.adapter = adapter


//            buttonAddTask.root.setOnClickListener {
//                onClickCreateTask(tabTasks)
//            }
        }
    }
}