package com.cupcake.jobsfinder.ui.jobs

import android.os.Bundle
import android.view.View
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentJobDetailsBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment
import com.cupcake.jobsfinder.ui.jobs.adapter.ViewPagerJobDetailsAdapter
import com.google.android.material.tabs.TabLayoutMediator


class JobDetails : BaseFragment<FragmentJobDetailsBinding, JobViewModel>(
    R.layout.fragment_job_details,
    JobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName
    private val fragmentTasks = mapOf(
        0 to AboutJobCategory(),
        1 to FragmenastAboutCompanyCategory(),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabName = listOf<String>(" About job", " About company")
        val adapter = ViewPagerJobDetailsAdapter(
            fragmentManager = requireActivity().supportFragmentManager,
            fragmentItems = fragmentTasks,
            lifecycle = lifecycle,
        )
        binding?.apply {
            viewPagerCategory.adapter = adapter
            TabLayoutMediator(tabLayoutCategory,viewPagerCategory){ tab ,position ->
                tab.text=tabName[position]
            }.attach()

        }
    }

}
