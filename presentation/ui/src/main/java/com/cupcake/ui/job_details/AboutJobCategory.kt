package com.cupcake.ui.job_details


import android.os.Bundle
import android.view.View
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.ItemAboutJobCategoryBinding
import com.cupcake.viewmodels.job_details.JobDetailsViewModel

class AboutJobCategory : BaseFragment<ItemAboutJobCategoryBinding, JobDetailsViewModel>(
    R.layout.item_about_job_category,
    JobDetailsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
