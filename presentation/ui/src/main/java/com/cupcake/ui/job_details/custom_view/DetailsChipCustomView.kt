package com.cupcake.ui.job_details.custom_view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.cupcake.ui.databinding.ItemJobDetailsCardsBinding
import com.cupcake.ui.utill.convert

class DetailsChipCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: ItemJobDetailsCardsBinding = ItemJobDetailsCardsBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun workType(workTypeValue: String) {
        binding.textViewDetailsContractType.text = workTypeValue
    }

    fun location(locationValue: String) {
        binding.textViewDetailsLocation.text = locationValue
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun createdAt(time: String) {
        binding.textViewDetailsPostedOn.text = convert(time)
    }

    fun salary(salaryValue: String) {
        binding.textViewDetailsSalary.text = salaryValue
    }

    fun experience(experience: String) {
        binding.textViewDetailsExperience.text = experience
    }

}