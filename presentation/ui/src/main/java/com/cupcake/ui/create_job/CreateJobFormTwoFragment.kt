package com.cupcake.ui.create_job

import android.os.Bundle
import android.util.Log
import android.view.View
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.ItemCreateJobFormTwoBinding
import com.cupcake.viewmodels.create_job.CreateJobViewModel

class CreateJobFormTwoFragment : BaseFragment<ItemCreateJobFormTwoBinding, CreateJobViewModel>(
    R.layout.item_create_job_form_two, CreateJobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onChangeValueSlider()
    }

    private fun onChangeValueSlider() {
        binding.rangeSliderSalary.addOnChangeListener { slider, value, fromUser ->
            viewModel.onChangeRangSalary(slider.values)

        }
    }
}