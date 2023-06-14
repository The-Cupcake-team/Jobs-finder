package com.cupcake.jobsfinder.ui.jobs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.cupcake.ui.databinding.CardViewDetailsChipBinding

class DetailsChipCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr){
    private val binding: CardViewDetailsChipBinding = CardViewDetailsChipBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )
    fun workType(workTypeValue:String){
        binding.textViewDetailsContractType.text=workTypeValue
    }
    fun location(locationValue:String){
        binding.textViewDetailsLocation.text=locationValue
    }
    fun jobType(jobTypeValue:String){
        binding.textViewDetailsWorkPlace.text=jobTypeValue
    }
    fun createdAt(createdAtValue:String){
        binding.textViewDetailsPostedOn.text=createdAtValue
    }
    fun salary(salaryValue:String){
        binding.textViewDetailsSalary.text=salaryValue
    }

}