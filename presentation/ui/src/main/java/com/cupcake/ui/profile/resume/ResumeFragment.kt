package com.cupcake.ui.profile.resume

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.transition.Visibility
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentResumeBinding
import com.cupcake.ui.utill.makeGone
import com.cupcake.ui.utill.makeVisible
import com.cupcake.viewmodels.profile.resume.ResumeViewModel

class ResumeFragment : BaseFragment<FragmentResumeBinding, ResumeViewModel>(
    R.layout.fragment_resume,
    ResumeViewModel::class.java
) {
    override val LOG_TAG: String = "ResumeFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            educationCard.setOnClickListener { toggleRecyclerViewVisibility(educationRecyclerView, educationViewAddResumeItem) }
            skillsCard.setOnClickListener { toggleRecyclerViewVisibility(skillsRecyclerView, skillsViewAddResumeItem) }
            employmentCard.setOnClickListener { toggleRecyclerViewVisibility(employmentRecyclerView, employmentViewAddResumeItem) }
            languagesCard.setOnClickListener { toggleRecyclerViewVisibility(languagesRecyclerView, languagesViewAddResumeItem) }
            coursesCard.setOnClickListener { toggleRecyclerViewVisibility(coursesRecyclerView, coursesViewAddResumeItem) }
        }
    }

    private fun toggleRecyclerViewVisibility(recyclerView: RecyclerView, addResumeItem: ImageView) {
        if (recyclerView.visibility == View.GONE) {
            TransitionManager.beginDelayedTransition(recyclerView, AutoTransition())
            recyclerView.makeVisible()
            addResumeItem.setImageResource(R.drawable.ic_card_open)
        } else {
            recyclerView.makeGone()
            addResumeItem.setImageResource(R.drawable.add)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResumeFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}