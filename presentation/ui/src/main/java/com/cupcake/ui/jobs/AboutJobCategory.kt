package com.cupcake.ui.jobs


import android.os.Bundle
import android.view.View
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentAboutJobCategoryBinding
import com.cupcake.viewmodels.job_details.JobViewModel

class AboutJobCategory : BaseFragment<FragmentAboutJobCategoryBinding, JobViewModel>(
    R.layout.fragment_about_job_category,
    JobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
