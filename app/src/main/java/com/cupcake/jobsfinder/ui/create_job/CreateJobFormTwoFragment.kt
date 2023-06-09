package com.cupcake.jobsfinder.ui.create_job

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentCreateJobBinding
import com.cupcake.jobsfinder.databinding.ItemCreateJobFormTwoBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment

class CreateJobFormTwoFragment : BaseFragment<ItemCreateJobFormTwoBinding, CreateJobViewModel>(
    R.layout.item_create_job_form_two, CreateJobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

}