package com.cupcake.jobsfinder.ui.jobs


import android.app.Dialog
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.view.Window
import android.widget.Toast
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
        showDialog()
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
    fun showDialog(){
        binding?.buttonApplyJob?.setOnClickListener {
            if (binding!!.dialogLayout.visibility==View.GONE){
                binding!!.dialogLayout.visibility=View.VISIBLE
            val dialog= Dialog(requireContext())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.card_dialog_apply_job)
            binding!!.buttonSend.setOnClickListener {
                Toast.makeText(requireActivity(), "cv", Toast.LENGTH_SHORT).show()
            }
            binding!!.buttonCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
            }
        }
    }
}
