package com.cupcake.jobsfinder.ui.jobs


import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentAboutJobCategoryBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment

class AboutJobCategory : BaseFragment<FragmentAboutJobCategoryBinding, JobViewModel>(
    R.layout.fragment_about_job_category,
    JobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exapandapleCard()
    }

    private fun exapandapleCard() {
        binding?.textViewDescriptionTitle?.setOnClickListener {
            binding?.apply {
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
}
