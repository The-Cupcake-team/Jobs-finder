package com.cupcake.ui.job_details.custom_view

import android.content.Context
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.cupcake.ui.R
import com.cupcake.ui.databinding.JobDetailsExpandableCardBinding

class ExpandCardCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: JobDetailsExpandableCardBinding =
        JobDetailsExpandableCardBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    init {
        cardDescriptionExpand()
        cardQualificationExpand()
        cardRequiredSkillsExpand()
    }

    fun cardDescription(value: String) {
        binding.textViewDescription.text = value
    }

    private fun cardDescriptionExpand() {
        binding.textViewDescriptionTitle.setOnClickListener {
            binding.apply {
                if (textViewDescription.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(
                        cardViewExpandableDescription,
                        AutoTransition()
                    )
                    textViewDescription.visibility = View.VISIBLE
                    textViewDescriptionTitle.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_card_open,
                        0
                    )
                } else {
                    textViewDescription.visibility = View.GONE
                    textViewDescriptionTitle.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_card_closed,
                        0
                    )
                }
            }
        }
    }


    private fun cardQualificationExpand() {
        binding.textViewQualificationTitle.setOnClickListener {
            binding.apply {
                if (textViewQualification.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(
                        cardViewExpandableDescription,
                        AutoTransition()
                    )
                    textViewQualification.visibility = View.VISIBLE
                    textViewQualificationTitle.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_card_open,
                        0
                    )
                } else {
                    textViewQualification.visibility = View.GONE
                    textViewQualificationTitle.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_card_closed,
                        0
                    )
                }
            }
        }
    }

    private fun cardRequiredSkillsExpand() {
        binding.textViewRequiredSkillsTitle.setOnClickListener {
            binding.apply {
                if (chipGroupRequiredSkills.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(
                        cardViewExpandableDescription,
                        AutoTransition()
                    )
                    chipGroupRequiredSkills.visibility = View.VISIBLE
                    textViewRequiredSkillsTitle.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_card_open,
                        0
                    )
                } else {
                    chipGroupRequiredSkills.visibility = View.GONE
                    textViewRequiredSkillsTitle.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_card_closed,
                        0
                    )
                }
            }
        }
    }
}
