package com.cupcake.jobsfinder.ui.jobs

import android.os.Bundle
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentJobDetailsBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment


class JobDetails : BaseFragment<FragmentJobDetailsBinding, JobViewModel>(
    R.layout.fragment_job_details,
    JobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installJobViewPagerAdapter()
    }

    private fun installJobViewPagerAdapter() {
        val aboutJobCategoryFragment = AboutJobCategory()
        val aboutCompanyFragment = FragmentAboutCompanyCategory()
        val listOfFragment = listOf(aboutJobCategoryFragment, aboutCompanyFragment)
        val jobViewPager = JobPagerAdapter(this, listOfFragment)
        binding?.viewPagerCategory?.adapter = jobViewPager
    }

}