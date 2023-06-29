package com.cupcake.ui.profile

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentProfileResumeBinding
import com.cupcake.ui.utill.makeGone
import com.cupcake.ui.utill.makeVisible
import com.cupcake.viewmodels.jobs.JobsEvent
import com.cupcake.viewmodels.profile.ProfileResumeViewModel
import com.cupcake.viewmodels.profile.ResumeEvent
import com.cupcake.viewmodels.profile.resume.skill.SkillsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfileResumeFragment : BaseFragment<FragmentProfileResumeBinding, ProfileResumeViewModel>(
    R.layout.fragment_profile_resume,
    ProfileResumeViewModel::class.java
) {
    override val LOG_TAG: String = "ResumeFragment"
    private lateinit var adapterEducation: EducationAdapter
    private lateinit var adapterSkills: SkillsAdapter
    private lateinit  var sillsUiState: MutableList<SkillsUiState>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inti()
        setupEducationsRecyclerView()
        setupSkillsRecyclerView()
        handelResumeEvent()
    }

    private fun setupEducationsRecyclerView() {
        adapterEducation = EducationAdapter(listOf(), viewModel)
        binding.educationRecyclerView.adapter=adapterEducation
    }
    private fun loadEducationData() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.state.collect { state ->
                adapterEducation.setData(state.educationUiState)
            }
        }
    }


    private fun setupSkillsRecyclerView() {
        adapterSkills = SkillsAdapter(listOf(), viewModel)
        binding.skillsRecyclerView.adapter=adapterSkills
    }
    private fun loadSkillsData() {

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.state.collect { state ->
                adapterSkills.setData(state.sillsUiState)

            }

        }
    }

    private fun toggleRecyclerViewVisibility(recyclerView: View, addResumeItem: ImageView,loadData: ()->Unit) {
        if (recyclerView.visibility == View.GONE) {
            TransitionManager.beginDelayedTransition(recyclerView as ViewGroup?, AutoTransition())
            recyclerView.makeVisible()
            loadData()
            addResumeItem.setImageResource(R.drawable.ic_card_open)
        } else {
            recyclerView.makeGone()
            addResumeItem.setImageResource(R.drawable.add)
        }
    }

    private fun  inti(){
        binding.apply {
            educationCard.setOnClickListener {
                toggleRecyclerViewVisibility(
                    educationRecyclerViewP,
                    educationViewAddResumeItem
            ) { loadEducationData() }
            }
            skillsCard.setOnClickListener {
                toggleRecyclerViewVisibility(skillsRecyclerViewP, skillsViewAddResumeItem){loadSkillsData() } }
//            employmentCard.setOnClickListener { toggleRecyclerViewVisibility(employmentRecyclerViewP, employmentViewAddResumeItem) }
//            languagesCard.setOnClickListener { toggleRecyclerViewVisibility(languagesRecyclerViewP, languagesViewAddResumeItem) }
//            coursesCard.setOnClickListener { toggleRecyclerViewVisibility(coursesRecyclerViewP, coursesViewAddResumeItem) }
        }
    }
    private fun handelResumeEvent() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.event.collect { resumeEvent ->
                when (resumeEvent) {
                    is ResumeEvent.DeleteSkill -> {
                        loadSkillsData()
                    }

                    is ResumeEvent.EditeEducation ->{
                        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToProfileEducationFragment(
                            resumeEvent.fromAddButton,resumeEvent.education
                        ))
                    }

                    is ResumeEvent.AddEducation -> {
                        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToProfileEducationFragment(
                            resumeEvent.fromAddButton,resumeEvent.education
                        ))
                    }

                    ResumeEvent.CrateSkill -> {


                    }
                }
            }
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