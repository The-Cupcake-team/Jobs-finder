package com.cupcake.ui.profile

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentProfileResumeBinding
import com.cupcake.ui.utill.makeGone
import com.cupcake.ui.utill.makeVisible
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
        private lateinit var jobsEventJob: Job


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inti()
        setupEducationsRecyclerView()
        setupSkillsRecyclerView()
    }

    private fun setupEducationsRecyclerView() {
        adapterEducation = EducationAdapter(listOf(), viewModel)
        binding.educationRecyclerView.adapter = adapterEducation
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
        binding.skillsRecyclerView.adapter = adapterSkills
    }

    private fun loadSkillsData() {

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.state.collect { state ->
                adapterSkills.setData(state.sillsUiState)

            }

        }
    }

    private fun toggleRecyclerViewVisibility(
        divider: View,
        recyclerView: View,
        add: ImageView,
        textView: TextView,
        drawableStart: Drawable?,
        addEditeText: EditText?,
        loadData: () -> Unit
    ) {
        if (recyclerView.visibility == View.GONE) {
            TransitionManager.beginDelayedTransition(recyclerView as ViewGroup?, AutoTransition())
            add.makeVisible()
            recyclerView.makeVisible()
            divider.makeVisible()
            addEditeText?.makeVisible()
            loadData()
            textView.setCompoundDrawablesWithIntrinsicBounds(
                drawableStart,
                null,
                ContextCompat.getDrawable(textView.context, R.drawable.ic_card_open),
                null
            )
        } else {
            recyclerView.makeGone()
            divider.makeGone()
            add.makeGone()
            addEditeText?.makeGone()

            textView.setCompoundDrawablesWithIntrinsicBounds(
                drawableStart,
                null,
                ContextCompat.getDrawable(textView.context, R.drawable.add),
                null
            )
        }
    }

    private fun inti() {
        binding.apply {
            textViewResume.setOnClickListener {
                toggleRecyclerViewVisibility(
                    educationViewAddResumeItemBorder,
                    educationRecyclerView,
                    educationViewAdd,
                    textViewResume,
                    ContextCompat.getDrawable(it.context, R.drawable.education),
                    null
                ) { loadEducationData() }
            }
            skillsViewResume.setOnClickListener {
                toggleRecyclerViewVisibility(
                    skillsViewAddResumeItemBorder,
                    skillsRecyclerView,
                    addSkillButton,
                    skillsViewResume,
                    ContextCompat.getDrawable(it.context, R.drawable.skills),
                    addSkillEditeText
                ) { loadSkillsData() }
            }
        }
    }

    private fun handelResumeEvent() {
        jobsEventJob=lifecycleScope.launch(Dispatchers.Main) {
            viewModel.event.collect { resumeEvent ->
                when (resumeEvent) {
                    is ResumeEvent.DeleteSkill -> {
                        loadSkillsData()
                    }

                    is ResumeEvent.EditeEducation -> {
                        findNavController().navigate(
                            ProfileFragmentDirections.actionProfileFragmentToProfileEducationFragment(
                                resumeEvent.fromAddButton, resumeEvent.education
                            )
                        )
                    }

                    is ResumeEvent.AddEducation -> {
                        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToProfileEducationFragment(
                            true,null
                        ))
                    }
                }
            }
        }
    }

    private fun crateSkill(){
        binding.addSkillButton.setOnClickListener {
            if(binding.addSkillEditeText.text.isNotEmpty()){
                viewModel.createSkill(binding.addSkillEditeText.text.toString())
                loadSkillsData()
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

    override fun onResume() {
        super.onResume()
        handelResumeEvent()
        loadEducationData()
        viewModel.getAllEducation()
        loadSkillsData()
        crateSkill()
    }

    override fun onPause() {
        super.onPause()
        jobsEventJob.cancel()

    }
}