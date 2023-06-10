package com.cupcake.jobsfinder.ui.jobs

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentJobDetailsBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment
import com.cupcake.jobsfinder.ui.jobs.adapter.ViewPagerJobDetailsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject


class JobDetails @Inject constructor(
): BaseFragment<FragmentJobDetailsBinding, JobViewModel>(
    R.layout.fragment_job_details,
    JobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setJobAdapter()
        applyClickHandler()
    }
    private fun setJobAdapter(){
        val fragmentTasks = mapOf(
            ABOUT_JOB_FRAGMENT to AboutJobCategory(),
            ABOUT_CATEGORY_FRAGMENT to FragmentAboutCompanyCategory(),
        )
        val adapter = ViewPagerJobDetailsAdapter(
            fragmentManager = requireActivity().supportFragmentManager,
            fragmentItems = fragmentTasks,
            lifecycle = lifecycle,
        )
        binding?.apply {
            viewPagerCategory.adapter = adapter
            setTabLayout(tabLayoutCategory, viewPagerCategory)
        }
    }

    private fun setTabLayout(tabLayoutCategory : TabLayout, viewPagerCategory : ViewPager2){
        val tabName = listOf("About job", " About company")
        TabLayoutMediator(tabLayoutCategory ,viewPagerCategory){ tab ,position ->
            tab.text= tabName[position]
        }.attach()
    }

    private fun applyClickHandler(){
        binding?.buttonApplyJob?.setOnClickListener {
            setDialog()
        }
    }
    private fun setDialog() {
        val dialog = Dialog(requireContext())
        dialog.show()
    }

    companion object{
        const val ABOUT_JOB_FRAGMENT = 0
        const val ABOUT_CATEGORY_FRAGMENT = 1
    }

}


