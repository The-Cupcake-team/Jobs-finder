package com.cupcake.ui.create_job

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.ItemCreateJobFormTwoBinding
import com.cupcake.viewmodels.create_job.CreateJobViewModel

class CreateJobFormTwoFragment : BaseFragment<ItemCreateJobFormTwoBinding, CreateJobViewModel>(
    R.layout.item_create_job_form_two, CreateJobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

}