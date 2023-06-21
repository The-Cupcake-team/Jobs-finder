package com.cupcake.ui.job_details


import android.os.Bundle
import android.view.View
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.ItemAboutJobCategoryBinding
import com.cupcake.viewmodels.job_details.JobViewModel

class AboutJobCategory : BaseFragment<ItemAboutJobCategoryBinding, JobViewModel>(
    R.layout.item_about_job_category,
    JobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
