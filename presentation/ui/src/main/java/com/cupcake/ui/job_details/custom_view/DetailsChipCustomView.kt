package com.cupcake.jobsfinder.ui.jobs

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.cupcake.ui.databinding.CardViewDetailsChipBinding
import com.cupcake.ui.utill.convert

class DetailsChipCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): LinearLayout(context, attrs, defStyleAttr){
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

    fun createdAt(createdAtValue:String){
        binding.textViewDetailsPostedOn.text=createdAtValue
    }
    fun salary(salaryValue:String){
        binding.textViewDetailsSalary.text=salaryValue
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertTime( time: Long){
        binding.textViewDetailsPostedOn.text = convert(time)
    }

}