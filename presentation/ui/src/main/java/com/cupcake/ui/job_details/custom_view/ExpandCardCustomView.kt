package com.cupcake.jobsfinder.ui.jobs

import android.content.Context
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.cupcake.ui.databinding.JobDescrptionExpandableCardBinding

class ExpandCardCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: JobDescrptionExpandableCardBinding = JobDescrptionExpandableCardBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )
    init {
        binding.textViewDescriptionTitle.setOnClickListener {
            binding.apply {
                if (textViewDescription.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(
                        cardViewExpandableDescription,
                        AutoTransition()
                    )
                    textViewDescription.visibility = View.VISIBLE
                } else textViewDescription.visibility = View.GONE
            }
        }
    }
    fun cardDescription(value:String){
        binding.textViewDescription.text=value
    }
}
