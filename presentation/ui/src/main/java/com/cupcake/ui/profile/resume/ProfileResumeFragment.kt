package com.cupcake.ui.profile.resume

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentProfileResumeBinding
import com.cupcake.ui.utill.makeGone
import com.cupcake.ui.utill.makeVisible
import com.cupcake.viewmodels.profile.resume.ProfileResumeViewModel

class ProfileResumeFragment : BaseFragment<FragmentProfileResumeBinding, ProfileResumeViewModel>(
    R.layout.fragment_profile_resume,
    ProfileResumeViewModel::class.java
) {
    override val LOG_TAG: String = "ResumeFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inti()
    }

    private fun toggleRecyclerViewVisibility(recyclerView: View, addResumeItem: ImageView) {
        if (recyclerView.visibility == View.GONE) {
            TransitionManager.beginDelayedTransition(recyclerView as ViewGroup?, AutoTransition())
            recyclerView.makeVisible()
            addResumeItem.setImageResource(R.drawable.ic_card_open)
        } else {
            recyclerView.makeGone()
            addResumeItem.setImageResource(R.drawable.add)
        }
    }

    private fun  inti(){
        binding.apply {
            educationCard.setOnClickListener { toggleRecyclerViewVisibility(educationRecyclerViewP, educationViewAddResumeItem) }
            skillsCard.setOnClickListener { toggleRecyclerViewVisibility(skillsRecyclerViewP, skillsViewAddResumeItem) }
            employmentCard.setOnClickListener { toggleRecyclerViewVisibility(employmentRecyclerViewP, employmentViewAddResumeItem) }
            languagesCard.setOnClickListener { toggleRecyclerViewVisibility(languagesRecyclerViewP, languagesViewAddResumeItem) }
            coursesCard.setOnClickListener { toggleRecyclerViewVisibility(coursesRecyclerViewP, coursesViewAddResumeItem) }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = ProfileResumeFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}